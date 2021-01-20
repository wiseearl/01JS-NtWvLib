package com.neonlight.ntwv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetworkCheck {

	private static final int CONNECT_TIMEOUT = 10000; // 連線逾時設定 10秒
	private static final int SOCKET_TIMEOUT = 60000; // 傳遞逾時設定 60秒

	/********************************************************/

	public static boolean checkNetwork(Context context){
		ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if(connMgr!=null){
			Network	network =connMgr.getActiveNetwork();
			if(network==null){
				return false;
			}else {
				NetworkCapabilities networkCapabilities = connMgr.getNetworkCapabilities(network);
				if(networkCapabilities!=null){
					return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
				}else {
					return false;
				}
			}
		}else {
			return false;
		}
	}



	public static String getTips(int aStatus) {
		// 伺服器端返回的狀態提示
		final String HTTP_0 = "未傳送";
		final String HTTP_1 = ""; // 成功
		final String HTTP_2 = "資料取得失敗"; // 失敗
		final String HTTP_403 = "403錯誤,連線網址禁止";
		final String HTTP_404 = "404錯誤,請求鏈結無效";
		final String HTTP_500 = "網路500錯誤,伺服器端程式出錯";
		final String HTTP_504 = "網路504錯誤,連線網址逾時";
		final String HTTP_900 = "網路傳輸協定出錯";
		final String HTTP_901 = "連接超時";
		final String HTTP_902 = "網路中斷";
		final String HTTP_903 = "網路資料流程傳輸出錯";
		final String HTTP_UNKONW = "未知的錯誤";
		// 自定義的提示
		String strTips = "";
		switch (aStatus) {
		case 0:
			strTips = HTTP_0;
			break;
		case 1:
			strTips = HTTP_1;
			break;
		case 2:
			strTips = HTTP_2;
			break;
		case 403:
			strTips = HTTP_403;
			break;
		case 404:
			strTips = HTTP_404;
			break;
		case 500:
			strTips = HTTP_500;
			break;
		case 504:
			strTips = HTTP_504;
			break;
		case 900:
			strTips = HTTP_900;
			break;
		case 901:
			strTips = HTTP_901;
			break;
		case 902:
			strTips = HTTP_902;
			break;
		case 903:
			strTips = HTTP_903;
			break;
		default:
			strTips = HTTP_UNKONW;
			break;
		}
		return strTips;
	}


}
