package cn.changhong.chcare.message;

public enum RouterType {
	FAMILY_PHOTOWALL_SERVICE_ROUTER("fpsr"), FAMILY_MEMBER_SERVICE_ROUTER(
			"fmsr"), FAMILY_MESSAGEBOARD_SERVICE_ROUTER("fmsr"), FAMILY_HEALTHMANAGER_SERVICE_ROUTER(
			"fhsr"), FAMILY_SYSTEM_SERVICE_ROUTER("fssr");
	private String value;

	private RouterType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
