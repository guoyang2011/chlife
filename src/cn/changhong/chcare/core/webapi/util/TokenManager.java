package cn.changhong.chcare.core.webapi.util;

public class TokenManager {
	private static String token = null;

	public static String getToken() {
		return token;
	}

	public static void setToken(String newToken) {
		token = newToken;
	}
}
