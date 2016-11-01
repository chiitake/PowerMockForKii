package practice.com.powermockforkii;

import android.text.TextUtils;

import com.kii.cloud.storage.Kii;
import com.kii.cloud.storage.KiiBucket;
import com.kii.cloud.storage.KiiObject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;

import static org.mockito.Matchers.anyString;

/**
 * 通信を行わないで、モックテストをする
 *
 * TextUtils
 *
 * [設定]
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(PowerMockRunner.class) // 1
@Config(packageName = "me.shopnews.app", constants = BuildConfig.class, sdk = 21)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"}) // 3
@PrepareForTest({SampleApi.class, Kii.class, KiiBucket.class, TextUtils.class})
public class SampleApiGetCouponMockTest {

	/**
	 * [エラー確認] ネットワークにつながっていない状態としてIOExceptionが発生することを確認
	 * @throws Exception
	 */
	@Test (expected = IOException.class)
	public void getCoupon_エラー_ネットワークにつながっていない() throws Exception {
		// setup

		// SampleApi内のKii.bucket("coupon_list")を呼び出したら、IOExceptionを発生させる
		PowerMockito.mockStatic(Kii.class);
		PowerMockito.when(Kii.bucket("coupon_list")).thenThrow(IOException.class);

		// TextUtilsをモックに。空なら true
		PowerMockito.mockStatic(TextUtils.class);
		PowerMockito.when(TextUtils.isEmpty(anyString())).thenReturn(false);
		PowerMockito.when(TextUtils.isEmpty("")).thenReturn(true);
		PowerMockito.when(TextUtils.isEmpty(null)).thenReturn(true);

		// spyする
		SampleApi orgSampleApi = new SampleApi();
		SampleApi spy = PowerMockito.spy(orgSampleApi);

		// test TextUtils
		Assert.assertEquals(true, TextUtils.isEmpty(null));
		Assert.assertEquals(true, TextUtils.isEmpty(""));
		Assert.assertEquals(false, TextUtils.isEmpty("abc"));

		// test
		String validCouponId = "validDummyCouponId";	// ダミーの有効なクーポンID
		KiiObject obj = spy.getCoupon(validCouponId);
	}

	/**
	 * [エラー確認] 空のcouponId(KiiObject id)を渡すと、OExceptionが発生することを確認
	 * @throws Exception
	 */
	@Test (expected = NullPointerException.class)
	public void getCoupon_無効な空ID() throws Exception {
		SampleApi sampleApi = new SampleApi();

		// TextUtilsをモックに。空なら true
		PowerMockito.mockStatic(TextUtils.class);
		PowerMockito.when(TextUtils.isEmpty(anyString())).thenReturn(false);
		PowerMockito.when(TextUtils.isEmpty("")).thenReturn(true);
		PowerMockito.when(TextUtils.isEmpty(null)).thenReturn(true);

		// test TextUtils
		Assert.assertEquals(true, TextUtils.isEmpty(null));
		Assert.assertEquals(true, TextUtils.isEmpty(""));
		Assert.assertEquals(false, TextUtils.isEmpty("abc"));

		// test
		String emptyCouponId = "";
		KiiObject obj = sampleApi.getCoupon(emptyCouponId);
	}
}