package br.ufpb.threadControl.MessengerConcurrent.TestThreadControl;

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
import br.ufpb.threadControl.MessengerConcurrent.Entity.Client;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Product;
import br.ufpb.threadControl.MessengerConcurrent.Entity.Promotion;
import br.ufpb.threadControl.MessengerConcurrent.Manager.ManagerClient;
import br.ufpb.threadControl.MessengerConcurrent.Manager.ManagerProduct;
import br.ufpb.threadControl.MessengerConcurrent.Manager.ManagerProductPreferences;
import br.ufpb.threadControl.MessengerConcurrent.Manager.ManagerPromotion;
import br.ufpb.threadControl.MessengerConcurrent.Manager.ManagerPurchasesOfProducts;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableAddClient;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableAddPreferencesClient;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableAddProduct;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableAddPromotion;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableEditClient;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableEditProduct;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableEditPromotion;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableGetHistoricalOfProductsPurchasedOfAllCustomers;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableGetHistoricalOfProductsPurchasedOfCustomers;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableGetListOfClient;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableGetListOfPreferredProductsOfAllCustomers;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableGetListOfPreferredProductsOfCustomers;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableGetListProduct;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableGetListPromotion;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnablePurchaseProduct;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableRemoveClient;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableRemoveHistoricalOfProductsPurchasedOfCustomers;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableRemovePreferencesClient;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableRemoveProduct;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableRemovePromotion;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableSearchClient;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableSearchProduct;
import br.ufpb.threadControl.MessengerConcurrent.Runnable.RunnableSearchPromotion;

public class FacadeTestWithConstante {
	public Facade facade;

	public BlockingQueue<BlockingQueue<Client>> copyListOfAllClient;
	public BlockingQueue<BlockingQueue<Product>> copyListOfAllProduct;
	public BlockingQueue<BlockingQueue<Promotion>> copyListOfAllPromotion;
	public BlockingQueue<Map<Client, List<Product>>> copyAllClientsPreferences;
	public BlockingQueue<Map<Client, List<Product>>> copyHistoricalOfProductsPurchasedOfAllCustomers;
	public BlockingQueue<Client> takerClientList;
	public BlockingQueue<Product> takerProductList;
	public BlockingQueue<Promotion> takerPromotionList;
	public BlockingQueue<List<Product>> takerProductPreferredClient;
	public BlockingQueue<List<Product>> takerHistoricalOfProductsPurchasedOfCustomers;
	public ThreadControl threadControl;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Starting the test facade class...");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Finished the test facade class!");
	}

	@Before
	public void setUp() throws Exception {
		facade = Facade.getInstance();
		threadControl = new ThreadControl();

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

	}

	@After
	public void tearDown() throws Exception {

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

		threadControl.prepare(getListOfClientFinishedState());
		threadControl.proceed();

		facade.getListOfClient(copyListOfAllClient);

		try {
			currentObjectsList = copyListOfAllClient.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		threadControl.waitUntilStateIsReached();

		assertTrue(currentObjectsList.contains(client1));
		assertFalse(currentObjectsList.contains(client2));
		assertEquals(2, currentObjectsList.size());
		threadControl.proceed();
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
		facade.addClient(client3);

		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getListOfClientFinishedState());
		threadControl.proceed();

		facade.getListOfClient(copyListOfAllClient);

		try {
			currentObjectsList = copyListOfAllClient.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		threadControl.waitUntilStateIsReached();

		assertEquals(2, currentObjectsList.size());
		assertTrue(currentObjectsList.contains(client1));

		threadControl.prepare(getRemoveClientFinishedState());
		threadControl.proceed();

		facade.removeClient(client1);

		threadControl.waitUntilStateIsReached();

		assertEquals(1, currentObjectsList.size());
		assertFalse(currentObjectsList.contains(client1));
		threadControl.proceed();
	}

	@Test
	public void testEditClient() {

		BlockingQueue<Client> listAux = new LinkedBlockingQueue<Client>();
		Client client = null;

		Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
				18, 11, 1988);

		threadControl.prepare(getAddClientFinishedState());
		facade.addClient(client1);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getSearchClientFinishedState());
		threadControl.proceed();
		facade.searchClient("diego.sousa@dce.ufpb.br", listAux);

		try {
			client = listAux.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		threadControl.waitUntilStateIsReached();

		assertEquals("Diego", client.getName());
		client1.setName("João");

		threadControl.prepare(getEditClientFinishedState());
		threadControl.proceed();

		facade.editClient(client1);

		threadControl.waitUntilStateIsReached();

		assertEquals("João", client1.getName());
		threadControl.proceed();
	}

	@Test
	public void testSearchClient() {

		Client clientAux = null;

		Client client = new Client("Diego", "34221048",
				"diego.sousa@dce.ufpb.br", 18, 11, 1988);

		threadControl.prepare(getAddClientFinishedState());
		facade.addClient(client);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getSearchClientFinishedState());
		threadControl.proceed();

		facade.searchClient("diego.sousa@dce.ufpb.br", takerClientList);

		try {
			clientAux = takerClientList.take();
		} catch (Exception e) {
			e.printStackTrace();
		}
		threadControl.waitUntilStateIsReached();

		assertEquals("Diego", clientAux.getName());
		assertNotNull(clientAux);
		threadControl.proceed();
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
		facade.addClient(client2);
		facade.addClient(client3);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getListOfClientFinishedState());
		threadControl.proceed();

		facade.getListOfClient(copyListOfAllClient);

		try {
			currentObjectsList = copyListOfAllClient.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		threadControl.waitUntilStateIsReached();

		assertEquals(3, currentObjectsList.size());
		assertTrue(currentObjectsList.contains(client1));
		threadControl.proceed();

	}

	@Test
	public void testAddProduct() {

		Product productAux = null;
		Product product = new Product("Book", 1234, 2.30, 100);

		threadControl.prepare(getAddProductFinishedState());
		facade.addProduct(product);
		threadControl.waitUntilStateIsReached();

		facade.searchProduct(1234, takerProductList);
		threadControl.proceed();
		try {
			productAux = takerProductList.take();
		} catch (InterruptedException e) {
			e.getMessage();
		}
		threadControl.waitUntilStateIsReached();
		assertNotNull(productAux);
		assertEquals("Book", productAux.getName());
		threadControl.proceed();
	}

	@Test
	public void testRemoveProduct() {

		BlockingQueue<Product> currentObjectsList = null;

		Product product = new Product("Book", 1234, 2.30, 100);
		Product product2 = new Product("table", 4321, 3.00, 100);
		Product product3 = new Product("printer", 1334, 1.20, 100);

		threadControl.prepare(getAddProductFinishedState());
		facade.addProduct(product);
		facade.addProduct(product2);
		facade.addProduct(product3);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getListOfProductFinishedState());
		threadControl.proceed();
		facade.getListProduct(copyListOfAllProduct);

		try {
			currentObjectsList = copyListOfAllProduct.take();
		} catch (Exception e) {
			e.getMessage();
		}

		threadControl.waitUntilStateIsReached();

		assertEquals(3, currentObjectsList.size());

		threadControl.prepare(getRemoveProductFinishedState());
		threadControl.proceed();

		facade.removeProduct(product);
		facade.removeProduct(product2);

		copyListOfAllProduct.clear();// Just emptying the list to use it again
		currentObjectsList = null;
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getListOfProductFinishedState());
		threadControl.proceed();
		facade.getListProduct(copyListOfAllProduct);

		try {
			currentObjectsList = copyListOfAllProduct.take();
		} catch (Exception e) {
			e.getMessage();
		}
		threadControl.waitUntilStateIsReached();

		assertEquals(1, currentObjectsList.size());
		assertFalse(currentObjectsList.contains(product));
		assertTrue(currentObjectsList.contains(product3));
		threadControl.proceed();
	}

	@Test
	public void testEditProduct() {
		BlockingQueue<Product> currentObjectsList = null;

		Product product = new Product("Book", 1234, 2.30, 100);

		threadControl.prepare(getAddProductFinishedState());
		facade.addProduct(product);
		threadControl.waitUntilStateIsReached();

		assertTrue(product.getPrice() == 2.30);

		product.setPrice(2.45);

		threadControl.prepare(getEditProductFinishedState());
		threadControl.proceed();
		facade.editProduct(product);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getListOfProductFinishedState());
		threadControl.proceed();
		facade.getListProduct(copyListOfAllProduct);

		try {
			currentObjectsList = copyListOfAllProduct.take();
		} catch (Exception e) {
			e.getMessage();
		}

		threadControl.waitUntilStateIsReached();

		assertTrue(currentObjectsList.contains(product));
		assertTrue(product.getPrice() == 2.45);
		assertFalse(product.getPrice() == 2.30);
		threadControl.proceed();
	}

	@Test
	public void testSearchProduct() {
		Product productAux = null;
		Product product = new Product("Book", 1234, 2.30, 100);

		threadControl.prepare(getAddProductFinishedState());
		facade.addProduct(product);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getSearchProductFinishedState());
		threadControl.proceed();
		facade.searchProduct(1234, takerProductList);

		try {
			productAux = takerProductList.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadControl.waitUntilStateIsReached();

		assertEquals("Book", productAux.getName());
		assertFalse(productAux.getName() == "Table");
		threadControl.proceed();
	}

	@Test
	public void testGetListProduct() {

		BlockingQueue<Product> currentObjectsList = null;

		Product product = new Product("Book", 1234, 2.30, 100);
		Product product2 = new Product("Table", 4321, 3.00, 100);
		Product product3 = new Product("printer", 1334, 1.20, 100);

		threadControl.prepare(getAddProductFinishedState());

		facade.addProduct(product);
		facade.addProduct(product2);
		facade.addProduct(product3);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getListOfProductFinishedState());
		threadControl.proceed();
		facade.getListProduct(copyListOfAllProduct);

		try {
			currentObjectsList = copyListOfAllProduct.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadControl.waitUntilStateIsReached();

		assertFalse(currentObjectsList.isEmpty());
		assertEquals(3, currentObjectsList.size());
		assertTrue(currentObjectsList.contains(product));
		threadControl.proceed();
	}

	@Test
	public void testAddPromotion() {

		BlockingQueue<Promotion> promotionsAux = null;

		Product book = new Product("Book", 1234, 2.30, 100);
		Product table = new Product("Table", 4321, 3.00, 100);
		Product printer = new Product("Printer", 1334, 1.20, 100);

		Promotion promotion = new Promotion(book, 1.00, 1245);
		Promotion promotion2 = new Promotion(table, 1.00, 1246);
		Promotion promotion3 = new Promotion(table, 1.00, 1247);

		threadControl.prepare(getAddProductFinishedState());
		facade.addProduct(book);
		facade.addProduct(table);
		facade.addProduct(printer);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getAddPromotionFinishedState());
		threadControl.proceed();
		facade.addPromotion(promotion);
		facade.addPromotion(promotion2);
		facade.addPromotion(promotion3);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getListOfPromotionFinishedState());
		threadControl.proceed();
		facade.getListPromotion(copyListOfAllPromotion);

		try {
			promotionsAux = copyListOfAllPromotion.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadControl.waitUntilStateIsReached();

		assertEquals(3, promotionsAux.size());
		assertFalse(promotionsAux.size() == 2);
		assertTrue(promotionsAux.contains(promotion));
		threadControl.proceed();
	}

	@Test
	public void testRemovePromotion() {

		BlockingQueue<Promotion> promotionAux = null;

		Product book = new Product("Book", 1234, 2.30, 100);
		Product table = new Product("Table", 4321, 3.00, 100);
		Product printer = new Product("Printer", 1334, 1.20, 100);

		Promotion promotion = new Promotion(book, 1.00, 1245);
		Promotion promotion2 = new Promotion(table, 1.00, 1246);
		Promotion promotion3 = new Promotion(table, 1.00, 1247);

		threadControl.prepare(getAddProductFinishedState());
		facade.addProduct(book);
		facade.addProduct(table);
		facade.addProduct(printer);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getAddPromotionFinishedState());
		threadControl.proceed();
		facade.addPromotion(promotion);
		facade.addPromotion(promotion2);
		facade.addPromotion(promotion3);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getListOfPromotionFinishedState());
		threadControl.proceed();
		facade.getListPromotion(copyListOfAllPromotion);

		try {
			promotionAux = copyListOfAllPromotion.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadControl.waitUntilStateIsReached();

		assertEquals(3, promotionAux.size());
		assertFalse(promotionAux.size() == 2);
		assertTrue(promotionAux.contains(promotion));
		threadControl.proceed();

		threadControl.prepare(getRemovePromotionFinishedState());

		facade.removePromotion(promotion);
		facade.removePromotion(promotion2);

		threadControl.waitUntilStateIsReached();

		assertEquals(1, promotionAux.size());
		assertTrue(promotionAux.contains(promotion3));
		assertFalse(promotionAux.contains(promotion));
		threadControl.proceed();
	}

	@Test
	public void testEditPromotion() {

		BlockingQueue<Promotion> promotionAux = null;

		Product book = new Product("Book", 1234, 2.30, 100);
		Promotion promotion = new Promotion(book, 1.00, 1245);

		threadControl.prepare(getAddProductFinishedState());
		facade.addProduct(book);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getAddPromotionFinishedState());
		threadControl.proceed();
		facade.addPromotion(promotion);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getListOfPromotionFinishedState());
		threadControl.proceed();
		facade.getListPromotion(copyListOfAllPromotion);

		try {
			promotionAux = copyListOfAllPromotion.take();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		threadControl.waitUntilStateIsReached();

		assertEquals(1, promotionAux.size());
		assertTrue(promotionAux.contains(promotion));
		assertTrue(promotionAux.peek().getDiscountedPrice() == 1.00);
		threadControl.proceed();

		promotion.setDiscountedPrice(1.20);

		threadControl.prepare(getEditPromotionFinishedState());
		facade.editPromotion(promotion);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getListOfPromotionFinishedState());
		threadControl.proceed();
		facade.getListPromotion(copyListOfAllPromotion);

		try {
			promotionAux = copyListOfAllPromotion.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadControl.waitUntilStateIsReached();
		assertTrue(promotionAux.peek().getDiscountedPrice() == 1.20);
		assertFalse(promotionAux.peek().getDiscountedPrice() == 1.00);
		threadControl.proceed();

	}

	@Test
	public void testSearchPromotion() {

		Promotion promotionAux = null;

		Product book = new Product("Book", 1234, 2.30, 100);
		Promotion promotion = new Promotion(book, 1.00, 1245);

		threadControl.prepare(getAddProductFinishedState());
		facade.addProduct(book);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getAddPromotionFinishedState());
		threadControl.proceed();
		facade.addPromotion(promotion);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getSearchPromotionFinishedState());
		threadControl.proceed();
		facade.searchPromotion(1245, takerPromotionList);

		try {
			promotionAux = takerPromotionList.take();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		threadControl.waitUntilStateIsReached();

		assertTrue(promotionAux.getDiscountedPrice() == 1.00);
		assertNotNull(promotionAux);
		threadControl.proceed();
	}

	@Test
	public void testGetListPromotion() {

		BlockingQueue<Promotion> currentObjectsPromotion = null;

		Product book = new Product("Book", 1234, 2.30, 100);
		Product table = new Product("Table", 4321, 3.00, 100);
		Product printer = new Product("Printer", 1334, 1.20, 100);

		Promotion promotion = new Promotion(book, 1.00, 1245);
		Promotion promotion2 = new Promotion(table, 1.00, 1246);
		Promotion promotion3 = new Promotion(table, 1.00, 1247);

		threadControl.prepare(getAddProductFinishedState());
		facade.addProduct(book);
		facade.addProduct(table);
		facade.addProduct(printer);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getAddPromotionFinishedState());
		threadControl.proceed();
		facade.addPromotion(promotion);
		facade.addPromotion(promotion2);
		facade.addPromotion(promotion3);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getListOfPromotionFinishedState());
		threadControl.proceed();
		facade.getListPromotion(copyListOfAllPromotion);

		try {
			currentObjectsPromotion = copyListOfAllPromotion.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadControl.waitUntilStateIsReached();
		assertEquals(3, currentObjectsPromotion.size());
		assertTrue(currentObjectsPromotion.contains(promotion));
		threadControl.proceed();
	}

	@Test
	public void testAddPreferencesClient() {

		List<Product> productAux = null;
		Product book = new Product("Book", 1234, 2.30, 100);
		Product table = new Product("Table", 4321, 3.00, 100);
		Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
				18, 11, 1988);

		threadControl.prepare(getAddProductFinishedState());
		facade.addProduct(book);
		facade.addProduct(table);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getAddClientFinishedState());
		threadControl.proceed();
		facade.addClient(client1);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getAddPreferencesClientFinishedState());
		threadControl.proceed();
		facade.addPreferencesClient(client1, book);
		facade.addPreferencesClient(client1, table);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getListProductPreferredClientFinishedState());
		threadControl.proceed();
		facade.getListProductPreferredClient(takerProductPreferredClient,
				client1);

		try {
			productAux = takerProductPreferredClient.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadControl.waitUntilStateIsReached();

		assertTrue(productAux.contains(book));
		assertEquals(2, productAux.size());
		threadControl.proceed();
	}

	@Test
	public void testRemoveAllPreferencesClient() {

		List<Product> productAux = null;

		Product book = new Product("Book", 1234, 2.30, 100);
		Product table = new Product("Table", 4321, 3.00, 100);
		Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
				18, 11, 1988);

		threadControl.prepare(getAddProductFinishedState());
		facade.addProduct(book);
		facade.addProduct(table);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getAddClientFinishedState());
		threadControl.proceed();
		facade.addClient(client1);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getAddPreferencesClientFinishedState());
		threadControl.proceed();
		facade.addPreferencesClient(client1, book);
		facade.addPreferencesClient(client1, table);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getListProductPreferredClientFinishedState());
		threadControl.proceed();
		facade.getListProductPreferredClient(takerProductPreferredClient,
				client1);

		try {
			productAux = takerProductPreferredClient.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadControl.waitUntilStateIsReached();

		assertTrue(productAux.contains(book));
		assertEquals(2, productAux.size());

		threadControl.prepare(getRemoveAllPreferencesClientFinishedState());
		threadControl.proceed();
		facade.removeAllPreferencesClient(client1);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getListProductPreferredClientFinishedState());
		threadControl.proceed();
		facade.getListProductPreferredAllClients(copyAllClientsPreferences);
		threadControl.waitUntilStateIsReached();

		assertFalse(copyAllClientsPreferences.contains(client1));
		assertEquals(0, copyAllClientsPreferences.size());
		threadControl.proceed();
	}

	@Test
	public void testGetListProductPreferredClient() {

		List<Product> currentList = null;

		Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
				18, 11, 1988);
		Client client2 = new Client("Ayla", "222", "ayla@dce.ufpb.br", 18, 11,
				1988);

		Product book = new Product("Book", 1234, 2.30, 100);
		Product table = new Product("Table", 4321, 3.00, 100);

		threadControl.prepare(getAddProductFinishedState());
		facade.addProduct(book);
		facade.addProduct(table);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getAddClientFinishedState());
		threadControl.proceed();
		facade.addClient(client1);
		facade.addClient(client2);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getAddPreferencesClientFinishedState());
		threadControl.proceed();
		facade.addPreferencesClient(client1, book);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getListProductPreferredClientFinishedState());
		threadControl.proceed();
		facade.getListProductPreferredClient(takerProductPreferredClient,
				client1);

		try {
			currentList = takerProductPreferredClient.take();
		} catch (InterruptedException e) {
			e.getMessage();
		}

		threadControl.waitUntilStateIsReached();

		assertEquals(1, currentList.size());
		assertTrue(currentList.contains(book));
		assertFalse(currentList.contains(table));
		threadControl.proceed();

	}

	@Test
	public void getListProductPreferredAllClients() {

		Map<Client, List<Product>> currentList = null;

		Product book = new Product("Book", 1234, 2.30, 100);
		Product table = new Product("Table", 4321, 3.00, 100);
		Product printer = new Product("Printer", 4321, 3.00, 100);

		Client client1 = new Client("Diego", "111", "diego.sousa@dce.ufpb.br",
				18, 11, 1988);
		Client client2 = new Client("Ayla", "222", "ayla@dce.ufpb.br", 18, 11,
				1988);

		threadControl.prepare(getAddProductFinishedState());
		facade.addProduct(book);
		facade.addProduct(table);
		facade.addProduct(printer);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getAddClientFinishedState());
		threadControl.proceed();
		facade.addClient(client1);
		facade.addClient(client2);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getAddPreferencesClientFinishedState());
		threadControl.proceed();
		facade.addPreferencesClient(client1, book);
		facade.addPreferencesClient(client1, table);
		facade.addPreferencesClient(client2, printer);
		threadControl.waitUntilStateIsReached();

		threadControl
				.prepare(getListProductPreferredAllClientsFinishedState());
		threadControl.proceed();
		facade.getListProductPreferredAllClients(copyAllClientsPreferences);

		try {
			currentList = copyAllClientsPreferences.take();
		} catch (InterruptedException e) {
			e.getMessage();
		}
		threadControl.waitUntilStateIsReached();

		assertEquals(2, currentList.size());
		assertTrue(currentList.get(client1).contains(book));
		assertFalse(currentList.get(client1).contains(printer));
		assertTrue(currentList.containsKey(client2));
		assertEquals(2, currentList.get(client1).size());
		assertEquals(1, currentList.get(client2).size());
		threadControl.proceed();

	}

	@Test
	public void testBuyProduct() {

		List<Product> listAux = null;
		Product book = new Product("Book", 1234, 2.30, 100);
		Client client = new Client("Diego", "3423-1435", "bisneto@dce.ufpb.br",
				25, 11, 1988);

		threadControl.prepare(getAddClientFinishedState());
		facade.addClient(client);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getAddProductFinishedState());
		threadControl.proceed();
		facade.addProduct(book);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getBuyProductFinishedState());
		threadControl.proceed();
		facade.buyProduct(client, book, 2);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getHistoricalCustomerPurchaseFinishedState());
		threadControl.proceed();
		facade.getHistoricalCustomerPurchase(client,
				takerHistoricalOfProductsPurchasedOfCustomers);

		try {
			listAux = takerHistoricalOfProductsPurchasedOfCustomers.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadControl.waitUntilStateIsReached();

		assertTrue(listAux.contains(book));
		assertTrue(listAux.get(0).getQuantity() == 98);
		threadControl.proceed();
	}

	@Test
	public void removeHistoricalOfProductsPurchasedOfCustomers() {
		List<Product> listClient = null;
		Map<Client, List<Product>> listAllClient = null;

		Product book = new Product("Book", 1234, 2.30, 100);
		Product table = new Product("Table", 1234, 2.30, 100);
		Client client = new Client("Diego", "3423-1435",
				"diego.sousa@dce.ufpb.br", 29, 9, 1988);

		threadControl.prepare(getAddClientFinishedState());
		facade.addClient(client);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getAddProductFinishedState());
		threadControl.proceed();
		facade.addProduct(book);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getBuyProductFinishedState());
		threadControl.proceed();
		facade.buyProduct(client, book, 2);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getHistoricalCustomerPurchaseFinishedState());
		threadControl.proceed();
		facade.getHistoricalCustomerPurchase(client,
				takerHistoricalOfProductsPurchasedOfCustomers);
		threadControl.waitUntilStateIsReached();

		threadControl
				.prepare(getHistoricalPurchasesOfAllCustomerFinishedState());
		threadControl.proceed();
		facade.getHistoricalPurchasesOfAllCustomer(copyHistoricalOfProductsPurchasedOfAllCustomers);

		try {
			listClient = takerHistoricalOfProductsPurchasedOfCustomers.take();
			listAllClient = copyHistoricalOfProductsPurchasedOfAllCustomers
					.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadControl.waitUntilStateIsReached();

		assertEquals(1, listClient.size());
		assertTrue(listClient.contains(book));
		assertFalse(listClient.contains(table));

		assertEquals(1, listAllClient.size());
		assertTrue(listAllClient.get(client).contains(book));

		threadControl.proceed();

		listClient.clear();
		listAllClient.clear();

		threadControl
				.prepare(getRemoveHistoricalCustomerPurchaseFinishedState());
		facade.removeHistoricalCustomerPurchase(client);
		threadControl.waitUntilStateIsReached();

		threadControl
				.prepare(getHistoricalPurchasesOfAllCustomerFinishedState());
		threadControl.proceed();
		facade.getHistoricalPurchasesOfAllCustomer(copyHistoricalOfProductsPurchasedOfAllCustomers);

		try {
			listAllClient = copyHistoricalOfProductsPurchasedOfAllCustomers
					.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadControl.waitUntilStateIsReached();
		assertEquals(0, listAllClient.size());
		assertFalse(listAllClient.containsKey(client));
		assertTrue(listAllClient.isEmpty());
		threadControl.proceed();
	}

	@Test
	public void getHistoricalPurchasesOfAllCustomer() {

		List<Product> listAux = null;

		Product book = new Product("Book", 1234, 2.30, 100);
		Product table = new Product("Table", 1234, 2.30, 100);
		Client client = new Client("Diego", "3423-1435",
				"diego.sousa@dce.ufpb.br", 29, 9, 1988);

		threadControl.prepare(getAddClientFinishedState());
		facade.addClient(client);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getAddProductFinishedState());
		threadControl.proceed();
		facade.addProduct(book);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getBuyProductFinishedState());
		threadControl.proceed();
		facade.buyProduct(client, book, 2);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getHistoricalCustomerPurchaseFinishedState());
		threadControl.proceed();
		facade.getHistoricalCustomerPurchase(client,
				takerHistoricalOfProductsPurchasedOfCustomers);

		try {
			listAux = takerHistoricalOfProductsPurchasedOfCustomers.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadControl.waitUntilStateIsReached();

		assertEquals(1, listAux.size());
		assertTrue(listAux.contains(book));
		assertFalse(listAux.contains(table));
		threadControl.proceed();
	}

	@Test
	public void getHistoricProductsBuyAllClient() {

		Map<Client, List<Product>> listAux = null;

		Product book = new Product("Book", 1234, 2.30, 100);
		Product table = new Product("Table", 1234, 2.30, 100);
		Product printer = new Product("Printer", 1234, 2.30, 100);
		Product pencil = new Product("Pencil", 1234, 2.30, 100);

		Client client = new Client("Diego", "3423-1435",
				"diego.sousa@dce.ufpb.br", 29, 9, 1988);
		Client client2 = new Client("Ayla", "3423-1435", "ayla@dce.ufpb.br",
				29, 9, 1988);
		Client client3 = new Client("Maria", "3423-1435", "maria@dce.ufpb.br",
				29, 9, 1988);
		Client client4 = new Client("João", "3423-1435", "joao@dce.ufpb.br",
				29, 9, 1988);

		threadControl.prepare(getAddClientFinishedState());
		facade.addClient(client);
		facade.addClient(client2);
		facade.addClient(client3);
		facade.addClient(client4);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getAddProductFinishedState());
		threadControl.proceed();
		facade.addProduct(book);
		facade.addProduct(table);
		facade.addProduct(printer);
		facade.addProduct(pencil);
		threadControl.waitUntilStateIsReached();

		threadControl.prepare(getBuyProductFinishedState());
		threadControl.proceed();
		facade.buyProduct(client, book, 2);
		facade.buyProduct(client, table, 2);
		facade.buyProduct(client2, table, 2);
		facade.buyProduct(client2, printer, 2);
		facade.buyProduct(client3, pencil, 2);
		facade.buyProduct(client3, table, 2);
		facade.buyProduct(client4, pencil, 2);
		threadControl.waitUntilStateIsReached();

		threadControl
				.prepare(getHistoricalPurchasesOfAllCustomerFinishedState());
		threadControl.proceed();
		facade.getHistoricalPurchasesOfAllCustomer(copyHistoricalOfProductsPurchasedOfAllCustomers);

		try {
			listAux = copyHistoricalOfProductsPurchasedOfAllCustomers.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadControl.waitUntilStateIsReached();

		assertEquals(4, listAux.size());
		assertTrue(listAux.get(client).contains(book));
		assertEquals(2, listAux.get(client).size());
		assertFalse(listAux.get(client).contains(pencil));

		assertTrue(listAux.get(client2).contains(table));
		assertEquals(2, listAux.get(client2).size());
		assertFalse(listAux.get(client2).contains(book));

		assertTrue(listAux.get(client3).contains(table));
		assertEquals(2, listAux.get(client3).size());
		assertFalse(listAux.get(client3).contains(book));

		assertTrue(listAux.get(client4).contains(pencil));
		assertEquals(1, listAux.get(client4).size());
		assertFalse(listAux.get(client4).contains(book));
		threadControl.proceed();
	}

	// -------SystemConfiguration Generic--------

	public SystemConfiguration getGenericSystemConfiguration(
			String threadClassName, ThreadState expectedState,
			int timesToBeInState) {
		ThreadConfiguration config = new ThreadConfiguration(threadClassName,
				expectedState, timesToBeInState);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	// -------------- SystemConfiguration Client--------------------------------

	public SystemConfiguration getAddClientFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableAddClient.class.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getRemoveClientFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableRemoveClient.class.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getEditClientFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableEditClient.class.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getSearchClientFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableSearchClient.class.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getListOfClientFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableGetListOfClient.class.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	// ---------SystemConfiguration Product--------------

	public SystemConfiguration getAddProductFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableAddProduct.class.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getRemoveProductFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableRemoveProduct.class.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getEditProductFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableEditProduct.class.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getSearchProductFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableSearchProduct.class.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getListOfProductFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableGetListProduct.class.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	// ---------SystemConfiguration Promotion--------------

	public SystemConfiguration getAddPromotionFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableAddPromotion.class.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getRemovePromotionFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableRemovePromotion.class.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getEditPromotionFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableEditPromotion.class.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getSearchPromotionFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableSearchPromotion.class.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getListOfPromotionFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableGetListPromotion.class.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	// ---------SystemConfiguration PreferencesClient--------------

	public SystemConfiguration getAddPreferencesClientFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableAddPreferencesClient.class.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getListProductPreferredClientFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableGetListOfPreferredProductsOfCustomers.class
						.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getRemoveAllPreferencesClientFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableRemovePreferencesClient.class.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getListProductPreferredAllClientsFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableGetListOfPreferredProductsOfAllCustomers.class
						.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	// ---------SystemConfiguration Purchases Of Products--------------

	public SystemConfiguration getBuyProductFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnablePurchaseProduct.class.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getHistoricalCustomerPurchaseFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableGetHistoricalOfProductsPurchasedOfCustomers.class
						.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getHistoricalPurchasesOfAllCustomerFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableGetHistoricalOfProductsPurchasedOfAllCustomers.class
						.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

	public SystemConfiguration getRemoveHistoricalCustomerPurchaseFinishedState() {
		ThreadConfiguration config = new ThreadConfiguration(
				RunnableRemoveHistoricalOfProductsPurchasedOfCustomers.class
						.getCanonicalName(),
				ThreadState.FINISHED,
				ThreadConfiguration.ALL_THREADS_TO_BE_IN_STATE);
		ListOfThreadConfigurations sysConfig = new ListOfThreadConfigurations();
		sysConfig.addThreadConfiguration(config);
		return sysConfig;
	}

}
