package com.fanniemae.prism.gui.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author Beknazar Suranchiyev
 * 
 *         July 2018
 *
 */
public class BrowserUtils {
	public static WebElement fluentWait(final WebElement webElement, int timeinsec) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver())
				.withTimeout(timeinsec, TimeUnit.SECONDS).pollingEvery(timeinsec, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return webElement;
			}
		});
		return element;
	}

	public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void waitForPageToLoad(long timeOutInSeconds) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			System.out.println("Waiting for page to load...");
			WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
			wait.until(expectation);
		} catch (Throwable error) {
			System.out.println(
					"Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
		}
	}

	public static void waitForTextApear(WebElement element, String text, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
		wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
	}

	public static WebElement waitForVisibility(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static WebElement waitForClickablility(WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static WebElement waitForClickablility(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void waitFor(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void doAcceptAlertIfAny() {
		try {
			WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 2);
			wait.until(ExpectedConditions.alertIsPresent());
			Driver.getDriver().switchTo().alert().accept();

		} catch (TimeoutException e) {
			System.out.println("Could not find alert. Keeping quiet");
		}
	}

	public static String getTodayDate() {
		LocalDate now = LocalDate.now();
		return " Created by: " + DateTimeFormatter.ofPattern("dd MMM uuuu").format(now);
	}

	public static void uploadFile(WebElement inputEl, String fileName){
		File profile = new File("src/test/resources/com/fanniemae/prism/gui/testData/inputData/"+fileName);
		System.out.println(profile.getAbsolutePath());
		inputEl.sendKeys(profile.getAbsolutePath());
	}

	public static void insertMessageToGUI(String cssLocator, String message, MessageType type){
		waitForVisibility(By.cssSelector(cssLocator),10);
		JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
		String script = generateAlertMessage(cssLocator,message,type).toString();
		js.executeScript(script);
	}

	private static StringBuilder generateAlertMessage(String cssLocator,String message,MessageType type){
		StringBuilder sc = new StringBuilder("document.style = \"@import url('//maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css');\";");
		sc.append("var outEl = document.createElement('div'); ");
		sc.append("outEl.className='"+getMessageClassName(type)+"'; ");
		sc.append("outEl.style=\""+getMessageStyle(type)+"\"; ");

		sc.append("outEl.innerText='"+message+"'; ");

		sc.append("var innerEl = document.createElement('i'); ");
		sc.append("innerEl.className = '"+getItemClassName(type)+"'; ");
		sc.append("innerEl.style=\"padding-right: 1%;\";" );
		sc.append("outEl.insertBefore(innerEl,outEl.firstChild); ");

		sc.append("var elToInsert = document.querySelector(\""+cssLocator+"\"); ");
		sc.append("elToInsert.insertBefore(outEl,elToInsert.firstChild); ");
		return sc;
	}

	private static String getMessageStyle(MessageType type){
		Map<MessageType,String> styles = new HashMap<>();
		String baseStyle = "margin: 10px 0px; padding:12px; margin:10px 22px; font-size:1em; vertical-align:middle;";
		styles.put(MessageType.WARNING,baseStyle+"color: #9F6000; background-color: #FEEFB3;");
		styles.put(MessageType.INFO,baseStyle+"color: #00529B; background-color: #BDE5F8;");
		styles.put(MessageType.ERROR,baseStyle+"color: #D8000C; background-color: #FFD2D2;");
		styles.put(MessageType.SUCCESS,baseStyle+"color: #4F8A10; background-color: #DFF2BF;");
		return styles.get(type);
	}

	private static String getMessageClassName(MessageType type){
		Map<MessageType,String> styles = new HashMap<>();
		styles.put(MessageType.WARNING,"isa_warning");
		styles.put(MessageType.INFO,"isa_info");
		styles.put(MessageType.ERROR,"isa_error");
		styles.put(MessageType.SUCCESS,"isa_success");
		return styles.get(type);
	}

	private static String getItemClassName(MessageType type){
		Map<MessageType,String> styles = new HashMap<>();
		styles.put(MessageType.WARNING,"fa fa-warning");
		styles.put(MessageType.INFO,"fa fa-warning");
		styles.put(MessageType.ERROR,"fa fa-times-circle");
		styles.put(MessageType.SUCCESS,"fa fa-check");
		return styles.get(type);
	}




}
