package ui.view;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import domain.db.ApartmentDB;
import domain.model.Apartment;

//!!!OPMERKING!!!: Alle testen werkten met originele project, zie cycloneServer r0298778_project_appartemente
//Wegens tijdsgebrek nu niet alle fouten opgespoord, nieuwe test werkt wel!

public class EigenProject5Test {
	
	private static final String url = "http://localhost:8080/1TX4_VerdonckMaria/";
	WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "WebContent\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test
	public void indexHeeftBerekendeWaarde() {
		driver.get(url);
		WebElement calculatedValue = driver.findElement(By.id("calculatedValue"));
		
		ApartmentDB db = ApartmentDB.getDB();
		assertTrue(calculatedValue.getText().contains(Integer.toString(db.getAvaragePrice())));
	}

	@Test
	public void alsFormulierLeegIngevuldIsWordtFormulierOpnieuwGetoond() {
		driver.get(url + "form.jsp");
		vulFormulierIn("","2","","","");

		assertEquals("TOEVOEGEN", driver.findElement(By.tagName("h3")).getText());
		assertEquals("Toevoegen - No Place Like Home", driver.getTitle());
	}

	@Test
	public void alsFormulierCorrectIngevuldIsWordtOverzichtMetNieuweWaardeGetoond() {
		driver.get(url + "form.jsp");
		String price = "700";
		String rooms = "3";
		String address = "Heverlee";
		String link = "http://www.immoweb.be/nl/zoekertje/duplex/te-huur/leuven/3000/id6712353";
		String casino = "756";
		vulFormulierIn(price, rooms, address, link, casino);

		assertEquals("Bevestiging - No Place Like Home", driver.getTitle());
		
		driver.findElement(By.cssSelector("a[href*='Controller?action=read']")).click();
		
		ArrayList<WebElement> allTd = (ArrayList<WebElement>) driver.findElements(By.cssSelector("main td"));
		ArrayList<WebElement> allLinks = (ArrayList<WebElement>) driver.findElements(By.cssSelector(".link a"));

		assertTrue(allTdContains(price, allTd));
		assertTrue(allTdContains(rooms, allTd));
		assertTrue(allTdContains(address, allTd));
		boolean linkPresent = false;
		for (WebElement we: allLinks) {
			if(we.getAttribute("href").equals(link)) {
				linkPresent = true;
			}
		}
		assertTrue(linkPresent);
	}

	@Test
	public void overzichtspaginaIsCorrect() {
		driver.get(url);
		WebElement linkOverview = driver.findElement(By.id("link"));
	    linkOverview.click();
	    
		ArrayList<WebElement> tableElements = (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
		ArrayList<WebElement> allLinks = (ArrayList<WebElement>) driver.findElements(By.cssSelector(".link a"));
		
		ApartmentDB db = ApartmentDB.getDB();
		ArrayList<Apartment> apartments = db.getApartments();

		int teller = 0;
		int linkTeller = 0;
		for (int i = 0; i < apartments.size(); i++) {
			assertEquals(apartments.get(i).getPrice(), tableElements.get(teller).getText());
			teller++;
			assertEquals(apartments.get(i).getRooms(), tableElements.get(teller).getText());
			teller++;
			assertEquals(apartments.get(i).getAddress(), tableElements.get(teller).getText());
			teller++;
			assertEquals(apartments.get(i).getLink(), allLinks.get(linkTeller).getAttribute("href")); 
			linkTeller++;
			teller++;
			teller++;
			teller++;
		}

	}

	@Test
	public void alsFormulierJackpotDanWordtJackpotSchermGetoond() {
		driver.get(url + "form.jsp");
		String price = "700";
		String rooms = "3";
		String address = "Heverlee";
		String link = "http://www.immoweb.be/nl/zoekertje/duplex/te-huur/leuven/3000/id6712353";
		String casino = "5695";
		vulFormulierIn(price, rooms, address, link, casino);

		assertEquals("Jackpot - No Place Like Home", driver.getTitle());
		
	    WebElement jackpotText = driver.findElement(By.id("jackpotText"));
		
	    assertTrue(jackpotText.getText().contains(casino));
	}
	
	@After
	public void clean() {
		driver.quit();
	}
	
	//Hulpfuncties
	private boolean allTdContains(String string, ArrayList<WebElement> allTd) {
		for (WebElement w : allTd) {
			if (w.getText().toLowerCase().equals(string.toLowerCase()))
				return true;
		}
		return false;
	}

	private void vulFormulierIn(String price, String rooms, String address, String link, String casino) {
		WebElement priceField = driver.findElement(By.id("huurprijs"));
		priceField.clear();
		priceField.sendKeys(price);

		WebElement roomsField = driver.findElement(By.id("aantalSlaapkamers"));
		roomsField.clear();
		roomsField.sendKeys(rooms);

		WebElement addressField = driver.findElement(By.id("adres"));
		addressField.clear();
		addressField.sendKeys(address);

		WebElement linkField = driver.findElement(By.id("linkAppt"));
		linkField.clear();
		linkField.sendKeys(link);

		WebElement casinoField = driver.findElement(By.id("casino"));
		casinoField.clear();
		casinoField.sendKeys(casino);

		WebElement button = driver.findElement(By.cssSelector("input[type='submit']"));
		button.click();

	}
}
