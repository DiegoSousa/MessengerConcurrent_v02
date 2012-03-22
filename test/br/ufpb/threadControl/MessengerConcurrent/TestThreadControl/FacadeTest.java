package br.ufpb.threadControl.MessengerConcurrent.TestThreadControl;

import br.ufpb.threadControl.MessengerConcurrent.Runnable.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.ufcg.threadcontrol.ListOfThreadConfigurations;
import br.edu.ufcg.threadcontrol.SystemConfiguration;
import br.edu.ufcg.threadcontrol.ThreadConfiguration;
import br.edu.ufcg.threadcontrol.ThreadControl;
import br.edu.ufcg.threadcontrol.ThreadState;
import br.ufpb.threadControl.MessengerConcurrent.Controller.Facade;
import br.ufpb.threadControl.MessengerConcurrent.Entity.*;
import br.ufpb.threadControl.MessengerConcurrent.Manager.*;

public class FacadeTest {

	public static Facade facade;

	public static BlockingQueue<BlockingQueue<Client>> copyListOfAllClient;
	public static BlockingQueue<BlockingQueue<Product>> copyListOfAllProduct;
	public static BlockingQueue<BlockingQueue<Promotion>> copyListOfAllPromotion;
	public static BlockingQueue<Map<Client, List<Product>>> copyAllClientsPreferences;
	public static BlockingQueue<Map<Client, List<Product>>> copyHistoricalOfProductsPurchasedOfAllCustomers;
	ThreadControl threadControl = new ThreadControl();
	public static BlockingQueue<Client> takerClientList;
	public static BlockingQueue<Product> takerProductList;
	public static BlockingQueue<Promotion> takerPromotionList;
	public static BlockingQueue<List<Product>> takerProductPreferredClient;
	public static BlockingQueue<List<Product>> takerHistoricalOfProductsPurchasedOfCustomers;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		facade = Facade.getInstance();
		copyListOfAllClient = new LinkedBlockingQueue<BlockingQueue<Client>>();
		copyListOfAllProduct = new LinkedBlockingQueue<BlockingQueue<Product>>();
		copyListOfAllPromotion = new LinkedBlockingQueue<BlockingQueue<Promotion>>();
		copyAllClientsPreferences = new LinkedBlockingQueue<Map<Client, List<Product>>>();
		copyHistoricalOfProductsPurchasedOfAllCustomers = new LinkedBlockingQueue<Map<Client, List<Product>>>();

		takerClientList = new LinkedBlockingQueue<Client>();
		takerProductList = new LinkedBlockingQueue<Product>();
		takerPromotionList = new LinkedBlockingQueue<Promotion>();
		takerProductPreferredClient = new LinkedBlockingQueue<List<Product>>();
		takerHistoricalOfProductsPurchasedOfCustomers = new LinkedBlockingQueue<List<Product>>();

		System.out.println("Starting the test facade class...");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Finished the test facade class!");
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		copyListOfAllClient.clear();
		copyListOfAllProduct.clear();
		copyListOfAllPromotion.clear();
		copyAllClientsPreferences.clear();

		takerClientList.clear();
		takerProductList.clear();
		takerPromotionList.clear();
		takerProductPreferredClient.clear();
		takerHistoricalOfProductsPurchasedOfCustomers.clear();

		ManagerClient.getInstance().getListOfClient().clear();
		ManagerProduct.getInstance().getListProduct().clear();
		ManagerProductPreferences.getInstance()
				.getListOfPreferredProductsOfAllCustomers().clear();
		ManagerPromotion.getInstance().getListPromotion().clear();
		ManagerPurchasesOfProducts.getInstance()
				.getHistoricalOfProductsPurchasedOfAllCustomers().clear();
	}

	@Test
	public void testGetInstance() {
		assertNotNull(Facade.getInstance());
	}

	@Test
	public void testGetExecutor() {
		assertNotNull(facade.getExecutor());
	}

	@Test
	public void testAddClient() {
		BlockingQueue<Client> currentObjectsList = null;

		Client client1 = new Client("Diego", "111", "diego.sousa}@dce.ufpb.br",
				18, 11, 1988);
		Client client2 = new Client("Ayla", "222", "ayla@dce.ufpb.br", 18, 11,
				1988);
		Client client3 = new Client("Kawe", "333", "kawe.ramon@dce.ufpb.br",
				18, 11, 1988);

		threadControl.prepare(getAddClientFinishedState());

		facade.addClient(client1);
		facade.addClient(client3);

		threadControl.waitUntilStateIsReached();
		threadControl.proceed();

		threadControl.prepare(getListOfClientFinishedState());
		facade.getListOfClient(copyListOfAllClient);

		try {
			currentObjectsList = copyListOfAllClient.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		threadControl.waitUntilStateIsReached();
		threadControl.proceed();

		assertTrue(currentObjectsList.contains(client1));
		assertFalse(currentObjectsList.contains(client2));
		assertEquals(2, currentObjectsList.size());
	}

	@Test
	public void testRemoveClient() {
		BlockingQueue<Client> currentObjectsList = null;

		Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
				18, 11, 1988);
		Client client3 = new Client("Kawe", "333", "kawe.ramon@dce.ufpb.br",
				18, 11, 1988);

		threadControl.prepare(getAddClientFinishedState());

		facade.addClient(client1);

		threadControl.waitUntilStateIsReached();
		threadControl.proceed();

		threadControl.prepare(getAddClientFinishedState());

		facade.addClient(client3);

		threadControl.waitUntilStateIsReached();
		threadControl.proceed();

		threadControl.prepare(getListOfClientFinishedState());
		facade.getListOfClient(copyListOfAllClient);

		try {
			currentObjectsList = copyListOfAllClient.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		threadControl.waitUntilStateIsReached();
		threadControl.proceed();

		assertEquals(2, currentObjectsList.size());
		assertTrue(currentObjectsList.contains(client1));

		threadControl.prepare(getRemoveClientFinishedState());

		facade.removeClient(client1);

		threadControl.waitUntilStateIsReached();
		threadControl.proceed();

		assertEquals(1, currentObjectsList.size());
		assertFalse(currentObjectsList.contains(client1));
	}

	@Test
	public void testEditClient() {

		Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
				18, 11, 1988);

		threadControl.prepare(getAddClientFinishedState());
		facade.addClient(client1);
		threadControl.waitUntilStateIsReached();
		threadControl.proceed();

		assertEquals("Diego", client1.getName());
		client1.setName("João");

		threadControl.prepare(getEditClientFinishedState());
		facade.editClient(client1);
		threadControl.waitUntilStateIsReached();
		threadControl.proceed();

		assertEquals("João", client1.getName());

	}

	@Test
	public void testSearchClient() {

		Client clientAux = null;

		Client client = new Client("Diego", "34221048",
				"diego.sousa@dce.ufpb.br", 18, 11, 1988);

		threadControl.prepare(getAddClientFinishedState());
		facade.addClient(client);
		threadControl.waitUntilStateIsReached();
		threadControl.proceed();

		threadControl.prepare(getSearchClientFinishedState());
		facade.searchClient("diego.sousa@dce.ufpb.br", takerClientList);

		try {
			clientAux = takerClientList.take();
		} catch (Exception e) {
			e.printStackTrace();
		}
		threadControl.waitUntilStateIsReached();
		threadControl.proceed();

		assertEquals("Diego", clientAux.getName());
		assertNotNull(clientAux);
	}

	@Test
	public void testGetListOfClient() {

		BlockingQueue<Client> currentObjectsList = null;

		Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
				18, 11, 1988);
		Client client2 = new Client("Ayla", "222", "ayla@dce.ufpb.br", 18, 11,
				1988);
		Client client3 = new Client("Kawe", "333", "kawe.ramon@dce.ufpb.br",
				18, 11, 1988);

		threadControl.prepare(getAddClientFinishedState());
		facade.addClient(client1);
		threadControl.waitUntilStateIsReached();
		threadControl.proceed();

		threadControl.prepare(getAddClientFinishedState());
		facade.addClient(client2);
		threadControl.waitUntilStateIsReached();
		threadControl.proceed();

		threadControl.prepare(getAddClientFinishedState());
		facade.addClient(client3);
		threadControl.waitUntilStateIsReached();
		threadControl.proceed();

		threadControl.prepare(getListOfClientFinishedState());
		facade.getListOfClient(copyListOfAllClient);

		try {
			currentObjectsList = copyListOfAllClient.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		threadControl.waitUntilStateIsReached();
		threadControl.proceed();

		assertEquals(3, currentObjectsList.size());
		assertTrue(currentObjectsList.contains(client1));

	}

	//
	// @Test
	// public void testAddProduct() {
	//
	// Product productAux = null;
	//
	// Product product = new Product("Book", 1234, 2.30, 100);
	//
	// facade.addProduct(product);
	//
	// try {
	// Thread.sleep(3000);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// facade.searchProduct(1234, takerProductList);
	//
	// try {
	// productAux = takerProductList.take();
	// } catch (InterruptedException e) {
	// e.getMessage();
	// }
	//
	// assertNotNull(productAux);
	//
	// assertEquals("Book", productAux.getName());
	//
	// }
	//
	// @Test
	// public void testRemoveProduct() {
	//
	// BlockingQueue<Product> currentObjectsList = null;
	//
	// Product product = new Product("Book", 1234, 2.30, 100);
	// Product product2 = new Product("table", 4321, 3.00, 100);
	// Product product3 = new Product("printer", 1334, 1.20, 100);
	//
	// facade.addProduct(product);
	// facade.addProduct(product2);
	// facade.addProduct(product3);
	//
	// try {
	// Thread.sleep(3000);
	// } catch (InterruptedException e1) {
	// e1.printStackTrace();
	// }
	// facade.getListProduct(copyListOfAllProduct);
	//
	// try {
	// currentObjectsList = copyListOfAllProduct.take();
	// } catch (Exception e) {
	// e.getMessage();
	// }
	//
	// assertEquals(3, currentObjectsList.size());
	// try {
	// Thread.sleep(4000);
	// } catch (Exception e) {
	// e.getMessage();
	// }
	//
	// facade.removeProduct(product);
	// facade.removeProduct(product2);
	// copyListOfAllProduct.clear();
	//
	// try {
	// Thread.sleep(3000);
	// } catch (Exception e) {
	// e.getMessage();
	// }
	// facade.getListProduct(copyListOfAllProduct);
	//
	// try {
	// currentObjectsList = copyListOfAllProduct.take();
	// } catch (Exception e) {
	// e.getMessage();
	// }
	//
	// assertEquals(1, currentObjectsList.size());
	// assertFalse(currentObjectsList.contains(product));
	// assertTrue(currentObjectsList.contains(product3));
	// }
	//
	// @Test
	// public void testEditProduct() {
	// BlockingQueue<Product> currentObjectsList = null;
	//
	// Product product = new Product("Book", 1234, 2.30, 100);
	//
	// facade.addProduct(product);
	// assertTrue(product.getPrice() == 2.30);
	//
	// product.setPrice(2.45);
	// facade.editProduct(product);
	//
	// facade.getListProduct(copyListOfAllProduct);
	//
	// try {
	// currentObjectsList = copyListOfAllProduct.take();
	// } catch (Exception e) {
	// e.getMessage();
	// }
	//
	// assertTrue(currentObjectsList.contains(product));
	// assertTrue(product.getPrice() == 2.45);
	// assertFalse(product.getPrice() == 2.30);
	//
	// }
	//
	// @Test
	// public void testSearchProduct() {
	// Product productAux = null;
	// Product product = new Product("Book", 1234, 2.30, 100);
	//
	// facade.addProduct(product);
	// try {
	// Thread.sleep(3000);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// facade.searchProduct(1234, takerProductList);
	//
	// try {
	// productAux = takerProductList.take();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// assertEquals("Book", productAux.getName());
	// assertFalse(productAux.getName() == "Table");
	//
	// }
	//
	// @Test
	// public void testGetListProduct() {
	//
	// BlockingQueue<Product> currentObjectsList = null;
	//
	// Product product = new Product("Book", 1234, 2.30, 100);
	// Product product2 = new Product("Table", 4321, 3.00, 100);
	// Product product3 = new Product("printer", 1334, 1.20, 100);
	//
	// facade.addProduct(product);
	// facade.addProduct(product2);
	// facade.addProduct(product3);
	//
	// try {
	// Thread.sleep(4000);
	// } catch (InterruptedException erro) {
	// erro.getMessage();
	// }
	//
	// facade.getListProduct(copyListOfAllProduct);
	//
	// try {
	// currentObjectsList = copyListOfAllProduct.take();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// assertFalse(currentObjectsList.isEmpty());
	// assertEquals(3, currentObjectsList.size());
	// assertTrue(currentObjectsList.contains(product));
	//
	// }
	//
	// @Test
	// public void testAddPromotion() {
	//
	// BlockingQueue<Promotion> promotionsAux = null;
	//
	// Product book = new Product("Book", 1234, 2.30, 100);
	// Product table = new Product("Table", 4321, 3.00, 100);
	// Product printer = new Product("Printer", 1334, 1.20, 100);
	//
	// facade.addProduct(book);
	// facade.addProduct(table);
	// facade.addProduct(printer);
	//
	// Promotion promotion = new Promotion(book, 1.00, 1245);
	// Promotion promotion2 = new Promotion(table, 1.00, 1246);
	// Promotion promotion3 = new Promotion(table, 1.00, 1247);
	//
	// facade.addPromotion(promotion);
	// facade.addPromotion(promotion2);
	// facade.addPromotion(promotion3);
	//
	// try {
	// Thread.sleep(3000);
	// } catch (InterruptedException e1) {
	// e1.printStackTrace();
	// }
	//
	// facade.getListPromotion(copyListOfAllPromotion);
	//
	// try {
	// promotionsAux = copyListOfAllPromotion.take();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// assertEquals(3, promotionsAux.size());
	// assertFalse(promotionsAux.size() == 2);
	// assertTrue(promotionsAux.contains(promotion));
	// }
	//
	// @Test
	// public void testRemovePromotion() {
	//
	// BlockingQueue<Promotion> promotionAux = null;
	//
	// Product book = new Product("Book", 1234, 2.30, 100);
	// Product table = new Product("Table", 4321, 3.00, 100);
	// Product printer = new Product("Printer", 1334, 1.20, 100);
	//
	// facade.addProduct(book);
	// facade.addProduct(table);
	// facade.addProduct(printer);
	//
	// Promotion promotion = new Promotion(book, 1.00, 1245);
	// Promotion promotion2 = new Promotion(table, 1.00, 1246);
	// Promotion promotion3 = new Promotion(table, 1.00, 1247);
	//
	// facade.addPromotion(promotion);
	// facade.addPromotion(promotion2);
	// facade.addPromotion(promotion3);
	//
	// try {
	// Thread.sleep(3000);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// facade.getListPromotion(copyListOfAllPromotion);
	//
	// try {
	// promotionAux = copyListOfAllPromotion.take();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// assertEquals(3, promotionAux.size());
	// assertFalse(promotionAux.size() == 2);
	// assertTrue(promotionAux.contains(promotion));
	//
	// facade.removePromotion(promotion);
	// facade.removePromotion(promotion2);
	//
	// try {
	// Thread.sleep(3000);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// assertEquals(1, promotionAux.size());
	// assertTrue(promotionAux.contains(promotion3));
	// assertFalse(promotionAux.contains(promotion));
	// }
	//
	// @Test
	// public void testEditPromotion() {
	//
	// BlockingQueue<Promotion> promotionAux = null;
	//
	// Product book = new Product("Book", 1234, 2.30, 100);
	// Promotion promotion = new Promotion(book, 1.00, 1245);
	//
	// facade.addProduct(book);
	// facade.addPromotion(promotion);
	//
	// try {
	// Thread.sleep(4000);
	// } catch (InterruptedException e1) {
	// e1.printStackTrace();
	// }
	//
	// facade.getListPromotion(copyListOfAllPromotion);
	//
	// try {
	// promotionAux = copyListOfAllPromotion.take();
	// } catch (InterruptedException e1) {
	// e1.printStackTrace();
	// }
	//
	// assertEquals(1, promotionAux.size());
	// assertTrue(promotionAux.contains(promotion));
	// assertTrue(promotionAux.peek().getDiscountedPrice() == 1.00);
	//
	// promotion.setDiscountedPrice(1.20);
	// facade.editPromotion(promotion);
	// try {
	// Thread.sleep(4000);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// facade.getListPromotion(copyListOfAllPromotion);
	//
	// try {
	// promotionAux = copyListOfAllPromotion.take();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// assertTrue(promotionAux.peek().getDiscountedPrice() == 1.20);
	// assertFalse(promotionAux.peek().getDiscountedPrice() == 1.00);
	//
	// }
	//
	// @Test
	// public void testSearchPromotion() {
	//
	// Promotion promotionAux = null;
	//
	// Product book = new Product("Book", 1234, 2.30, 100);
	// Promotion promotion = new Promotion(book, 1.00, 1245);
	// facade.addProduct(book);
	// facade.addPromotion(promotion);
	//
	// try {
	// Thread.sleep(3000);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// facade.searchPromotion(1245, takerPromotionList);
	//
	// try {
	// promotionAux = takerPromotionList.take();
	// } catch (InterruptedException e1) {
	// e1.printStackTrace();
	// }
	//
	// assertTrue(promotionAux.getDiscountedPrice() == 1.00);
	// assertNotNull(promotionAux);
	//
	// }
	//
	// @Test
	// public void testGetListPromotion() {
	//
	// BlockingQueue<Promotion> currentObjectsPromotion = null;
	//
	// Product book = new Product("Book", 1234, 2.30, 100);
	// Product table = new Product("Table", 4321, 3.00, 100);
	// Product printer = new Product("Printer", 1334, 1.20, 100);
	//
	// Promotion promotion = new Promotion(book, 1.00, 1245);
	// Promotion promotion2 = new Promotion(table, 1.00, 1246);
	// Promotion promotion3 = new Promotion(table, 1.00, 1247);
	//
	// facade.addProduct(book);
	// facade.addProduct(table);
	// facade.addProduct(printer);
	//
	// facade.addPromotion(promotion);
	// facade.addPromotion(promotion2);
	// facade.addPromotion(promotion3);
	//
	// try {
	// Thread.sleep(4000);
	// } catch (InterruptedException e1) {
	// e1.getMessage();
	// }
	//
	// facade.getListPromotion(copyListOfAllPromotion);
	//
	// try {
	// currentObjectsPromotion = copyListOfAllPromotion.take();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// assertEquals(3, currentObjectsPromotion.size());
	// assertTrue(currentObjectsPromotion.contains(promotion));
	// }
	//
	// @Test
	// public void testAddPreferencesClient() {
	//
	// List<Product> productAux = null;
	// Product book = new Product("Book", 1234, 2.30, 100);
	// Product table = new Product("Table", 4321, 3.00, 100);
	// Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
	// 18, 11, 1988);
	//
	// facade.addProduct(book);
	// facade.addProduct(table);
	// facade.addClient(client1);
	//
	// facade.addPreferencesClient(client1, book);
	// facade.addPreferencesClient(client1, table);
	//
	// try {
	// Thread.sleep(3000);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// facade.getListProductPreferredClient(takerProductPreferredClient,
	// client1);
	//
	// try {
	// productAux = takerProductPreferredClient.take();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// assertTrue(productAux.contains(book));
	// assertEquals(2, productAux.size());
	// }
	//
	// @Test
	// public void testRemoveAllPreferencesClient() {
	//
	// List<Product> productAux = null;
	//
	// Product book = new Product("Book", 1234, 2.30, 100);
	// Product table = new Product("Table", 4321, 3.00, 100);
	//
	// facade.addProduct(book);
	// facade.addProduct(table);
	//
	// Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
	// 18, 11, 1988);
	//
	// facade.addClient(client1);
	//
	// facade.addPreferencesClient(client1, book);
	// facade.addPreferencesClient(client1, table);
	//
	// try {
	// Thread.sleep(3000);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// facade.getListProductPreferredClient(takerProductPreferredClient,
	// client1);
	//
	// try {
	// productAux = takerProductPreferredClient.take();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// assertTrue(productAux.contains(book));
	// assertEquals(2, productAux.size());
	//
	// facade.removeAllPreferencesClient(client1);
	//
	// try {
	// Thread.sleep(3000);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// facade.getListProductPreferredAllClients(copyAllClientsPreferences);
	//
	// assertFalse(copyAllClientsPreferences.contains(client1));
	// assertEquals(0, copyAllClientsPreferences.size());
	// }
	//
	// @Test
	// public void testGetListProductPreferredClient() {
	//
	// List<Product> currentList = null;
	//
	// Product book = new Product("Book", 1234, 2.30, 100);
	// Product table = new Product("Table", 4321, 3.00, 100);
	//
	// facade.addProduct(book);
	// facade.addProduct(table);
	//
	// Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
	// 18, 11, 1988);
	// Client client2 = new Client("Ayla", "222", "ayla@dce.ufpb.br", 18, 11,
	// 1988);
	//
	// facade.addClient(client1);
	// facade.addClient(client2);
	//
	// try {
	// Thread.sleep(3000);
	// } catch (InterruptedException e) {
	// e.getMessage();
	// }
	//
	// facade.addPreferencesClient(client1, book);
	//
	// try {
	// Thread.sleep(3000);
	// } catch (InterruptedException e) {
	// e.getMessage();
	// }
	//
	// facade.getListProductPreferredClient(takerProductPreferredClient,
	// client1);
	//
	// try {
	// currentList = takerProductPreferredClient.take();
	// } catch (InterruptedException e) {
	// e.getMessage();
	// }
	//
	// assertEquals(1, currentList.size());
	// assertTrue(currentList.contains(book));
	// assertFalse(currentList.contains(table));
	//
	// }
	//
	// @Test
	// public void getListProductPreferredAllClients() {
	//
	// Map<Client, List<Product>> currentList = null;
	//
	// Product book = new Product("Book", 1234, 2.30, 100);
	// Product table = new Product("Table", 4321, 3.00, 100);
	// Product printer = new Product("Printer", 4321, 3.00, 100);
	//
	// facade.addProduct(book);
	// facade.addProduct(table);
	// facade.addProduct(printer);
	//
	// Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
	// 18, 11, 1988);
	// Client client2 = new Client("Ayla", "222", "ayla@dce.ufpb.br", 18, 11,
	// 1988);
	//
	// facade.addClient(client1);
	// facade.addClient(client2);
	//
	// try {
	// Thread.sleep(3000);
	// } catch (InterruptedException e) {
	// e.getMessage();
	// }
	//
	// facade.addPreferencesClient(client1, book);
	// facade.addPreferencesClient(client1, table);
	// facade.addPreferencesClient(client2, printer);
	//
	// try {
	// Thread.sleep(3000);
	// } catch (InterruptedException e) {
	// e.getMessage();
	// }
	//
	// facade.getListProductPreferredAllClients(copyAllClientsPreferences);
	//
	// try {
	// currentList = copyAllClientsPreferences.take();
	// } catch (InterruptedException e) {
	// e.getMessage();
	// }
	//
	// assertEquals(2, currentList.size());
	// assertTrue(currentList.get(client1).contains(book));
	// assertFalse(currentList.get(client1).contains(printer));
	// assertTrue(currentList.containsKey(client2));
	// assertEquals(2, currentList.get(client1).size());
	// assertEquals(1, currentList.get(client2).size());
	//
	// }
	//
	// @Test
	// public void testBuyProduct() {
	//
	// List<Product> listAux = null;
	// Product book = new Product("Book", 1234, 2.30, 100);
	// Client client = new Client("Diego", "3423-1435", "bisneto@dce.ufpb.br",
	// 25, 11, 1988);
	//
	// facade.addClient(client);
	// facade.addProduct(book);
	//
	// facade.buyProduct(client, book, 2);
	//
	// try {
	// Thread.sleep(4000);
	// } catch (InterruptedException e1) {
	// e1.printStackTrace();
	// }
	//
	// facade.getHistoricalCustomerPurchase(client,
	// takerHistoricalOfProductsPurchasedOfCustomers);
	//
	// try {
	// listAux = takerHistoricalOfProductsPurchasedOfCustomers.take();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// assertTrue(listAux.contains(book));
	// assertTrue(listAux.get(0).getQuantity() == 98);
	// }
	//
	// @Test
	// public void removeHistoricalOfProductsPurchasedOfCustomers() {
	// List<Product> listClient = null;
	// Map<Client, List<Product>> listAllClient = null;
	//
	// Product book = new Product("Book", 1234, 2.30, 100);
	// Product table = new Product("Table", 1234, 2.30, 100);
	// Client client = new Client("Diego", "3423-1435",
	// "diego.sousa@dce.ufpb.br", 29, 9, 1988);
	//
	// facade.addClient(client);
	// facade.addProduct(book);
	// facade.buyProduct(client, book, 2);
	//
	// try {
	// Thread.sleep(4000);
	// } catch (InterruptedException e1) {
	// e1.printStackTrace();
	// }
	//
	// facade.getHistoricalCustomerPurchase(client,
	// takerHistoricalOfProductsPurchasedOfCustomers);
	// facade.getHistoricalPurchasesOfAllCustomer(copyHistoricalOfProductsPurchasedOfAllCustomers);
	//
	// try {
	// listClient = takerHistoricalOfProductsPurchasedOfCustomers.take();
	// listAllClient = copyHistoricalOfProductsPurchasedOfAllCustomers.take();
	//
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// assertEquals(1, listClient.size());
	// assertTrue(listClient.contains(book));
	// assertFalse(listClient.contains(table));
	//
	// assertEquals(1, listAllClient.size());
	// assertTrue(listAllClient.get(client).contains(book));
	//
	// listClient.clear();
	// listAllClient.clear();
	//
	// facade.removeHistoricalCustomerPurchase(client);
	//
	// try {
	// Thread.sleep(4000);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// facade.getHistoricalPurchasesOfAllCustomer(copyHistoricalOfProductsPurchasedOfAllCustomers);
	//
	// try {
	// listAllClient = copyHistoricalOfProductsPurchasedOfAllCustomers.take();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// assertEquals(0, listAllClient.size());
	// assertFalse(listAllClient.containsKey(client));
	// assertTrue(listAllClient.isEmpty());
	// }
	//
	// @Test
	// public void getHistoricalPurchasesOfAllCustomer() {
	//
	// List<Product> listAux = null;
	//
	// Product book = new Product("Book", 1234, 2.30, 100);
	// Product table = new Product("Table", 1234, 2.30, 100);
	// Client client = new Client("Diego", "3423-1435",
	// "diego.sousa@dce.ufpb.br", 29, 9, 1988);
	//
	// facade.addClient(client);
	// facade.addProduct(book);
	// facade.buyProduct(client, book, 2);
	//
	// try {
	// Thread.sleep(4000);
	// } catch (InterruptedException e1) {
	// e1.printStackTrace();
	// }
	//
	// facade.getHistoricalCustomerPurchase(client,
	// takerHistoricalOfProductsPurchasedOfCustomers);
	//
	// try {
	// listAux = takerHistoricalOfProductsPurchasedOfCustomers.take();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// assertEquals(1, listAux.size());
	// assertTrue(listAux.contains(book));
	// assertFalse(listAux.contains(table));
	//
	// }
	//
	// @Test
	// public void getHistoricProductsBuyAllClient() {
	//
	// Map<Client, List<Product>> listAux = null;
	//
	// Product book = new Product("Book", 1234, 2.30, 100);
	// Product table = new Product("Table", 1234, 2.30, 100);
	// Product printer = new Product("Printer", 1234, 2.30, 100);
	// Product pencil = new Product("Pencil", 1234, 2.30, 100);
	//
	// Client client = new Client("Diego", "3423-1435",
	// "diego.sousa@dce.ufpb.br", 29, 9, 1988);
	// Client client2 = new Client("Ayla", "3423-1435", "ayla@dce.ufpb.br",
	// 29, 9, 1988);
	// Client client3 = new Client("Maria", "3423-1435", "maria@dce.ufpb.br",
	// 29, 9, 1988);
	// Client client4 = new Client("João", "3423-1435", "joao@dce.ufpb.br",
	// 29, 9, 1988);
	//
	// facade.addClient(client);
	// facade.addClient(client2);
	// facade.addClient(client3);
	// facade.addClient(client4);
	//
	// facade.addProduct(book);
	// facade.addProduct(table);
	// facade.addProduct(printer);
	// facade.addProduct(pencil);
	//
	// facade.buyProduct(client, book, 2);
	// facade.buyProduct(client, table, 2);
	// facade.buyProduct(client2, table, 2);
	// facade.buyProduct(client2, printer, 2);
	// facade.buyProduct(client3, pencil, 2);
	// facade.buyProduct(client3, table, 2);
	// facade.buyProduct(client4, pencil, 2);
	//
	// try {
	// Thread.sleep(5000);
	// } catch (InterruptedException e1) {
	// e1.printStackTrace();
	// }
	//
	// facade.getHistoricalPurchasesOfAllCustomer(copyHistoricalOfProductsPurchasedOfAllCustomers);
	//
	// try {
	// listAux = copyHistoricalOfProductsPurchasedOfAllCustomers.take();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	//
	// assertEquals(4, listAux.size());
	// assertTrue(listAux.get(client).contains(book));
	// assertEquals(2, listAux.get(client).size());
	// assertFalse(listAux.get(client).contains(pencil));
	//
	// assertTrue(listAux.get(client2).contains(table));
	// assertEquals(2, listAux.get(client2).size());
	// assertFalse(listAux.get(client2).contains(book));
	//
	// assertTrue(listAux.get(client3).contains(table));
	// assertEquals(2, listAux.get(client3).size());
	// assertFalse(listAux.get(client3).contains(book));
	//
	// assertTrue(listAux.get(client4).contains(pencil));
	// assertEquals(1, listAux.get(client4).size());
	// assertFalse(listAux.get(client4).contains(book));
	// }

	// -------------- SystemConfiguration --------------------------------

	public SystemConfiguration getGenericSystemConfiguration(
			String threadClassName, ThreadState expectedState,
			int timesToBeInState) {
		ThreadConfiguration config = new ThreadConfiguration(threadClassName,
				expectedState, timesToBeInState);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getAddClientFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableAddClient.class.getCanonicalName(),
				ThreadState.FINISHED, ThreadConfiguration.AT_LEAST_ONCE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getRemoveClientFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableRemoveClient.class.getCanonicalName(),
				ThreadState.FINISHED, ThreadConfiguration.AT_LEAST_ONCE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getEditClientFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableEditClient.class.getCanonicalName(),
				ThreadState.FINISHED, ThreadConfiguration.AT_LEAST_ONCE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getSearchClientFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableSearchClient.class.getCanonicalName(),
				ThreadState.FINISHED, ThreadConfiguration.AT_LEAST_ONCE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getListOfClientFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableGetListOfClient.class.getCanonicalName(),
				ThreadState.FINISHED, ThreadConfiguration.AT_LEAST_ONCE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

}
