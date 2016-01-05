package com.wikia.webdriver.pageobjectsfactory.pageobject.discussions;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PostDetailsPage extends WikiBasePageObject{

  @FindBy(css = ".replies-list")
  private List<WebElement> repliesList;
  @FindBy(css = "svg.upvote")
  private WebElement upvoteButton;
  @FindBy(css = ".upvote-reply")
  private List<WebElement> replyUpvoteButton;
  @FindBy(css = "a.upvote-area")
  private WebElement upvoteArea;
  @FindBy(css = ".replies-list small")
  private List<WebElement> replyVoteCount;

  private static final String PATH = "d/p/%s";
  private static final String DEFAULT_POST_ID = "2621148372316194193";

  public PostDetailsPage(WebDriver driver) {
    super(driver);
  }

  public PostDetailsPage open(String wikiID) {
    driver.get(urlBuilder.getUrlForWiki().replace("/wiki", "") + String.format(PATH, wikiID));
    return this;
  }

  public PostDetailsPage open() {
    return open(DEFAULT_POST_ID);
  }

  public boolean isPostDetailsListEmpty() {
    return repliesList.isEmpty();
  }

  public boolean isUpvoteButtonVisible() {
    wait.forElementVisible(upvoteButton);
    return upvoteButton.isDisplayed();
  }

  public boolean isReplyUpvoteButtonVisible(int index) {
    WebElement button = replyUpvoteButton.get(index);
    wait.forElementVisible(button);
    return button.isDisplayed();
  }

  public void clickPostDetailsUpvoteButton() {
    wait.forElementClickable(upvoteButton);
    upvoteButton.click();
  }

  public void clickReplyUpvoteButton(int replyIndex) {
    WebElement button = replyUpvoteButton.get(replyIndex);
    wait.forElementClickable(button);
    button.click();
  }

  public String getPostDetailsVoteCount() {
    wait.forElementVisible(upvoteArea);
    return upvoteArea.getText();
  }

  public String getReplyVoteCount(int index) {
    WebElement voteCountArea = replyVoteCount.get(index);
    wait.forElementVisible(voteCountArea);
    return voteCountArea.getText();
  }

  public void waitForPostDetailsVoteCountToChange(String voteCount) {
    wait.forTextNotInElement(upvoteArea, voteCount);
  }

  public void waitForReplyVoteCountToChange(int replyIndex, String voteCount) {
    WebElement voteCountArea = replyVoteCount.get(replyIndex);
    wait.forTextNotInElement(voteCountArea, voteCount);
  }

}
