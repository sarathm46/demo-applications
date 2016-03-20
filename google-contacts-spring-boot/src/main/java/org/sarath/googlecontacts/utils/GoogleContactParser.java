package org.sarath.googlecontacts.utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import org.sarath.googlecontacts.model.GoogleContact;
import org.sarath.googlecontacts.model.GoogleEmail;
import org.sarath.googlecontacts.model.GoogleIm;
import org.sarath.googlecontacts.model.GooglePhoneNumber;
import org.springframework.stereotype.Component;

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.Link;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.contacts.GroupMembershipInfo;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.ExtendedProperty;
import com.google.gdata.data.extensions.Im;
import com.google.gdata.data.extensions.Name;
import com.google.gdata.data.extensions.PhoneNumber;
import com.google.gdata.util.ServiceException;

@Component
public class GoogleContactParser {
	public List<ContactEntry> getContacts(String token) throws ServiceException, IOException {
		ContactsService myService = new ContactsService("Spring boot application");
		myService.setPrivateHeader("Authorization", "Bearer " + token);
		URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full?max-results=1000");
		ContactFeed resultFeed = myService.getFeed(feedUrl, ContactFeed.class);
		System.out.println(resultFeed.getTitle().getPlainText());
		return resultFeed.getEntries();
	}

	public List<GoogleContact> getGoogleContacts(String token) throws ServiceException, IOException {
		List<ContactEntry> contactEntries = getContacts(token);
		List<GoogleContact> googleContacts = new ArrayList<>();

		for (ContactEntry entry : contactEntries) {
			GoogleContact googleContact = new GoogleContact();
			if (entry.hasName()) {
				Name name = entry.getName();
				if (name.hasFullName()) {
					String fullNameToDisplay = name.getFullName().getValue();
					if (name.getFullName().hasYomi()) {
						fullNameToDisplay += " (" + name.getFullName().getYomi() + ")";
					}
					// System.out.println("\t\t" + fullNameToDisplay);
					googleContact.setFullNameToDisplay(fullNameToDisplay);
				} else {
					// System.out.println("\t\t (no full name found)");
				}
				if (name.hasNamePrefix()) {
					// System.out.println("\t\t" +
					// name.getNamePrefix().getValue());
					googleContact.setNamePrefix(name.getNamePrefix().getValue());
				} else {
					// System.out.println("\t\t (no name prefix found)");
				}
				if (name.hasGivenName()) {
					String givenNameToDisplay = name.getGivenName().getValue();
					if (name.getGivenName().hasYomi()) {
						givenNameToDisplay += " (" + name.getGivenName().getYomi() + ")";
					}
					// System.out.println("\t\t" + givenNameToDisplay);
				} else {
					// System.out.println("\t\t (no given name found)");
				}
				if (name.hasAdditionalName()) {
					String additionalNameToDisplay = name.getAdditionalName().getValue();
					if (name.getAdditionalName().hasYomi()) {
						additionalNameToDisplay += " (" + name.getAdditionalName().getYomi() + ")";
					}
					googleContact.setAdditionalNameToDisplay(additionalNameToDisplay);

					// System.out.println("\t\t" + additionalNameToDisplay);
				} else {
					// System.out.println("\t\t (no additional name found)");
				}
				if (name.hasFamilyName()) {
					String familyNameToDisplay = name.getFamilyName().getValue();
					if (name.getFamilyName().hasYomi()) {
						familyNameToDisplay += " (" + name.getFamilyName().getYomi() + ")";
					}
					googleContact.setFamilyNameToDisplay(familyNameToDisplay);

					// System.out.println("\t\t" + familyNameToDisplay);
				} else {
					// System.out.println("\t\t (no family name found)");
				}
				if (name.hasNameSuffix()) {
					// System.out.println("\t\t" +
					// name.getNameSuffix().getValue());
					googleContact.setNamePrefix(name.getNameSuffix().getValue());
				} else {
					// System.out.println("\t\t (no name suffix found)");
				}
			} else {
				// System.out.println("\t (no name found)");
			}

			List<GooglePhoneNumber> googlePhoneNumbers = new ArrayList<>();
			for (PhoneNumber phone : entry.getPhoneNumbers()) {
				GooglePhoneNumber googlePhoneNumber = new GooglePhoneNumber();
				googlePhoneNumber.setPhoneNumber(phone.getPhoneNumber());
				googlePhoneNumbers.add(googlePhoneNumber);
			}
			googleContact.setPhoneNubers(googlePhoneNumbers);
			// System.out.println("Email addresses:");
			List<GoogleEmail> googleEmails = new ArrayList<>();
			for (Email email : entry.getEmailAddresses()) {
				GoogleEmail googleEmail = new GoogleEmail();
				googleEmail.setAddress(email.getAddress());
				System.out.print(" " + email.getAddress());
				if (email.getRel() != null) {
					// System.out.print(" rel:" + email.getRel());
					googleEmail.setRel(email.getRel());
				}
				if (email.getLabel() != null) {
					// System.out.print(" label:" + email.getLabel());
					googleEmail.setLabel(email.getLabel());
				}
				if (email.getPrimary()) {
					// System.out.print(" (primary) ");
				}
				googleEmail.setPrimary(email.getPrimary());
				// System.out.print("\n");

				googleEmails.add(googleEmail);
			}
			googleContact.setGoogleEmails(googleEmails);

			// System.out.println("IM addresses:");
			List<GoogleIm> googleIms = new ArrayList<>();
			for (Im im : entry.getImAddresses()) {
				GoogleIm googleIm = new GoogleIm();
				// System.out.print(" " + im.getAddress());
				googleIm.setAddress(im.getAddress());
				if (im.getLabel() != null) {
					// System.out.print(" label:" + im.getLabel());
					googleIm.setLabel(im.getLabel());
				}
				if (im.getRel() != null) {
					// System.out.print(" rel:" + im.getRel());
					googleIm.setRel(im.getRel());
				}
				if (im.getProtocol() != null) {
					// System.out.print(" protocol:" + im.getProtocol());
					googleIm.setProtocol(im.getProtocol());
				}
				googleIm.setPrimary(im.getPrimary());
				if (im.getPrimary()) {
					// System.out.print(" (primary) ");
				}
				// System.out.print("\n");
				googleIms.add(googleIm);
			}
			googleContact.setGoogleIms(googleIms);
			// System.out.println("Groups:");
			for (GroupMembershipInfo group : entry.getGroupMembershipInfos()) {
				String groupHref = group.getHref();
				// System.out.println(" Id: " + groupHref);
			}
			// System.out.println("Extended Properties:");
			for (ExtendedProperty property : entry.getExtendedProperties()) {
				if (property.getValue() != null) {
					// System.out.println(" " + property.getName() + "(value) =
					// " + property.getValue());
				} else if (property.getXmlBlob() != null) {
					// System.out.println(" " + property.getName() + "(xmlBlob)=
					// " + property.getXmlBlob().getBlob());
				}
			}
			Link photoLink = entry.getContactPhotoLink();
			String photoLinkHref = photoLink.getHref();
			googleContact.setPhotoLink(photoLinkHref + "?access_token=" + token);
			// System.out.println("Photo Link: " + photoLinkHref);
			if (photoLink.getEtag() != null) {
				// System.out.println("Contact Photo's ETag: " +
				// photoLink.getEtag());

			}
			// System.out.println("Contact's ETag: " + entry.getEtag());

			googleContacts.add(googleContact);
		}

		return googleContacts;

	}
}
