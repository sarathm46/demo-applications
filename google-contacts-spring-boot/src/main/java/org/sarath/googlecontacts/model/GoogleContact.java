package org.sarath.googlecontacts.model;

import java.util.List;

public class GoogleContact {
	private String fullNameToDisplay;
	private String givenNameToDisplay;
	private String namePrefix;
	private String nameSuffix;
	private String additionalNameToDisplay;
	private String familyNameToDisplay;

	private List<GoogleEmail> googleEmails;
	private List<GoogleIm> googleIms;

	private List<GooglePhoneNumber> phoneNubers;

	public List<GooglePhoneNumber> getPhoneNubers() {
		return phoneNubers;
	}

	public void setPhoneNubers(List<GooglePhoneNumber> phoneNubers) {
		this.phoneNubers = phoneNubers;
	}

	private String photoLink;

	public String getPhotoLink() {
		return photoLink;
	}

	public void setPhotoLink(String photoLink) {
		this.photoLink = photoLink;
	}

	public List<GoogleIm> getGoogleIms() {
		return googleIms;
	}

	public void setGoogleIms(List<GoogleIm> googleIms) {
		this.googleIms = googleIms;
	}

	public List<GoogleEmail> getGoogleEmails() {
		return googleEmails;
	}

	public void setGoogleEmails(List<GoogleEmail> googleEmails) {
		this.googleEmails = googleEmails;
	}

	public String getNameSuffix() {
		return nameSuffix;
	}

	public void setNameSuffix(String nameSuffix) {
		this.nameSuffix = nameSuffix;
	}

	public String getFamilyNameToDisplay() {
		return familyNameToDisplay;
	}

	public void setFamilyNameToDisplay(String familyNameToDisplay) {
		this.familyNameToDisplay = familyNameToDisplay;
	}

	public String getAdditionalNameToDisplay() {
		return additionalNameToDisplay;
	}

	public void setAdditionalNameToDisplay(String additionalNameToDisplay) {
		this.additionalNameToDisplay = additionalNameToDisplay;
	}

	public String getNamePrefix() {
		return namePrefix;
	}

	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	public String getGivenNameToDisplay() {
		return givenNameToDisplay;
	}

	public void setGivenNameToDisplay(String givenNameToDisplay) {
		this.givenNameToDisplay = givenNameToDisplay;
	}

	public String getFullNameToDisplay() {
		return fullNameToDisplay;
	}

	public void setFullNameToDisplay(String fullNameToDisplay) {
		this.fullNameToDisplay = fullNameToDisplay;
	}

}
