package com.orange.helpers;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MailValidator {

	public String RESETLINK;
	public String PROFILEVALUE;
	public String ACTIVATIONLINK;
	// public int unReadMessageCount;

	public int unReadMessageCount;
	public Folder folder = null;
	public Store store = null;
	//console value to retrive link
	// $('img[alt="Activate"]').parentElement.attributes[3].value

	// open the mail and keep it active
	public void openMailConnection(String userName, String password, String imapType, String folderName) {
		try {
			if (store == null) {
				System.out.println("***READING MAILBOX...");
				Properties props = new Properties();
				Session session = Session.getInstance(props);
				store = session.getStore("imaps");
				// store.connect("imap.mail.yahoo.com", userName, password);
				store.connect(imapType, userName, password);
			}
			folder = store.getFolder(folderName.toUpperCase());
			// changed it to ReadWrite from Read
			folder.open(Folder.READ_WRITE);
		} catch (MessagingException messagingException) {
			messagingException.printStackTrace();
		}

	}

	// close the mail and end the session
	public void closeMailConnections() {
		if (folder != null) {
			try {
				// expunge is required for deleting mails
				folder.expunge();
				folder.close(true);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		if (store != null) {
			try {
				store.close();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

	// read the mails count by using the active mail
	public void readUnReadMailsCount(int unReadMailsCount) {
		try {
			if (folder.getUnreadMessageCount() > unReadMailsCount) {
				unReadMessageCount = folder.getUnreadMessageCount();
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public boolean getUnReadMessageCount(String emailId, String pwd, String imapType, String folderName) {
		// openMailConnection("pwdtestevokeproduction@gmail.com",
		// "Testing@123","imap.gmail.com","INBOX");
		openMailConnection(emailId, pwd, imapType, folderName);
		readUnReadMailsCount(0);
		System.out.println("Un read mail" + unReadMessageCount);
		if (unReadMessageCount > 0) {
			return true;
		}
		System.out.println("no new unread mail received");
		return false;
	}

	// read the mail when subject equals type
	public void readMailContent(String subjectToBe) {
		switch (subjectToBe) {
		case "Hello Rewards Account Password Reset":
			verifyPWDResetMail();
			break;

		case "Welcome to Hello Rewards!":
			verifyActivationMail();
			break;

		}
	}

	// for activation reset mail
	public void verifyActivationMail() {
		System.out.println("In verify activation mail method");
		try {
			Message[] messages = folder.getMessages();
			for (int i = 0; i < messages.length; i++) {
				Message message1 = messages[i];
				Address[] from;
				from = message1.getFrom();
				System.out.println("-------------------------------");
				System.out.println("Date : " + message1.getSentDate());
				System.out.println("From : " + from[0]);
				System.out.println("Subject: " + message1.getSubject());
				System.out.println("Content type :" + message1.getContentType());
				// Calendar calendar = Calendar.getInstance();
				// calendar.setTime(message1.getSentDate());
				System.out.println("Date today " + DateUtils.getCurrentDayForMailValidator());
				if (message1.getSubject().contains("Welcome to Hello Rewards!")
						&& message1.getSentDate().toString().contains(DateUtils.getCurrentDayForMailValidator())) {
					System.out.println("Reading Message with subject : " + message1.getSubject());
					Multipart multipart = (Multipart) messages[i].getContent();
					BodyPart bodyPart = multipart.getBodyPart(1);// dont change ever
					String disposition = bodyPart.getDisposition();
					if (disposition != null && (disposition.equals(BodyPart.ATTACHMENT))) {
						System.out.println("Mail have some attachment : ");

						DataHandler handler = bodyPart.getDataHandler();
						System.out.println("file name : " + handler.getName());
					} else {
						System.out.println("reading content");
						String result = (String) bodyPart.getContent();
						// System.out.println(result);
						Elements allLinks = Jsoup.parse(result).body().getElementsByTag("a");
						for (Element link : allLinks) {
							// System.out.println("link value is -> "+link);
							if (link.getElementsByTag("img").attr("alt").contains("Activate")) {
								String linkHref = link.attr("href");
								System.out.println("OKTA URL:: " + linkHref);
								setACTIVATIONLINK(linkHref);
							}
						}
						Elements spans = Jsoup.parse(result).body().getElementsByTag("span");
						for (Element span : spans) {
							if (span.text().contains("Member Number: ")) {
								System.out.println("member value is -> " + span.text());
								setPROFILEVALUE(span.text());
							}

						}
					}
					// marking message as Deleted after the read operation is completed
					message1.setFlag(Flags.Flag.DELETED, true);
					System.out.println("Message with subject -> " + message1.getSubject() + " is deleted");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeMailConnections();
		}
	}

	// for password reset mail
	public void verifyPWDResetMail() {
		try {
			Message[] messages = folder.getMessages();
			for (int i = 0; i < messages.length; i++) {
				Message message1 = messages[i];
				Address[] from;
				from = message1.getFrom();
				System.out.println("-------------------------------");
				System.out.println("Date : " + message1.getSentDate());
				System.out.println("From : " + from[0]);
				System.out.println("Subject: " + message1.getSubject());
				System.out.println("Content type :" + message1.getContentType());
				System.out.println("Date today " + DateUtils.getCurrentDayForMailValidator());
				
				if (message1.getSubject().contains("Hello Rewards Account Password Reset")
						&& message1.getSentDate().toString().contains(DateUtils.getCurrentDayForMailValidator())) {
					System.out.println("Reading Message with subject : " + message1.getSubject());
					Multipart multipart = (Multipart) messages[i].getContent();
					BodyPart bodyPart = multipart.getBodyPart(1);// dont change ever
					String disposition = bodyPart.getDisposition();
					if (disposition != null && (disposition.equals(BodyPart.ATTACHMENT))) {
						System.out.println("Mail have some attachment : ");

						DataHandler handler = bodyPart.getDataHandler();
						System.out.println("file name : " + handler.getName());
					} else {
						System.out.println("reading content");
						String result = (String) bodyPart.getContent();
						// System.out.println(result);
						Elements allLinks = Jsoup.parse(result).body().getElementsByTag("a");
						for (Element link : allLinks) {
							String linkHref = link.attr("href");
							String linkText = link.text();
							if (linkText.equals("RESET PASSWORD")) {
								System.out.println("Text::" + linkText + ", URL::" + linkHref);
								setRESETLINK(linkHref);

							}
							Elements spans = link.getElementsByTag("span");
							for (Element span : spans) {
								if (span.text().contains("7")) {
									System.out.println("member value is -> " + span.text());

									setPROFILEVALUE(span.text());
								}
							}
						}
					}
					// marking message as Deleted after the read operation is completed
					message1.setFlag(Flags.Flag.DELETED, true);
					System.out.println("Message with subject -> " + message1.getSubject() + " is deleted");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeMailConnections();
		}
	}

	public String getSubjectText(String confirmationId) {
		try {
			Message[] messages = folder.getMessages();
			for (int i = 0; i < messages.length; i++) {
				Message message1 = messages[i];
				Address[] from;
				from = message1.getFrom();
				System.out.println("-------------------------------");
				System.out.println("Date : " + message1.getSentDate());
				System.out.println("From : " + from[0]);
				System.out.println("Subject: " + message1.getSubject());
				System.out.println("Content type :" + message1.getContentType());
				if (message1.getSubject().contains(confirmationId)) {
					String subject = message1.getSubject();
					System.out.println("Message with subject -> " + message1.getSubject() + " is deleted");
					message1.setFlag(Flags.Flag.DELETED, true);
					return subject;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeMailConnections();
		}
		return null;
	}

	public String getRESETLINK() {
		readMailContent("Hello Rewards Account Password Reset");
		return RESETLINK;
	}

	public String getACTIVATIONLINK() {
		readMailContent("Welcome to Hello Rewards!");
		return ACTIVATIONLINK;
	}

	public String getPROFILEVALUE() {
		return PROFILEVALUE;
	}

	public void setRESETLINK(String rESETLINK) {
		RESETLINK = rESETLINK;
	}

	private void setPROFILEVALUE(String profileValue) {
		PROFILEVALUE = profileValue;
		System.out.println(PROFILEVALUE + " is the profile value retrieved");
	}

	private void setACTIVATIONLINK(String activationLink) {
		ACTIVATIONLINK = activationLink;
		System.out.println(ACTIVATIONLINK + " is the link retrieved");
	}
}
