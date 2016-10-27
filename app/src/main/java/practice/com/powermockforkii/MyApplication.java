package practice.com.powermockforkii;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.kii.cloud.storage.Kii;

/**
 * Created by natty on 2016/10/27.
 */

public class MyApplication  extends MultiDexApplication {
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

	@Override
	public void onCreate() {
		super.onCreate();

		// AppName: Kii Tutorial
		// ご自分の KiiTutorialのアカウントを記述 または kiiAccount.propertiesファイルに記述
		String KII_CLOUD_APP_ID = "";
		String KII_CLOUD_APP_KEY = "";

		// Gradleで kiiAccount.propertiesファイルを読み込む
		String appId = (TextUtils.isEmpty(BuildConfig.KII_API_ID)) ? KII_CLOUD_APP_ID : BuildConfig.KII_API_ID;
		String appKey = (TextUtils.isEmpty(BuildConfig.KII_API_KEY)) ? KII_CLOUD_APP_KEY : BuildConfig.KII_API_KEY;

		if (TextUtils.isEmpty(appId) || TextUtils.isEmpty(appKey)) {
			throw new NullPointerException("KiiCouldのappId or appKeyが指定されていません");
		}

		// Initialize Kii client SDK
		Kii.initialize(appId, appKey, Kii.Site.US);
	}

	/**
	 * 開放処理
	 */
	public void release() {

	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}
}
