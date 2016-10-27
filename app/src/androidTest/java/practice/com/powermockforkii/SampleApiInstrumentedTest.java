package practice.com.powermockforkii;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.kii.cloud.storage.KiiObject;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * [ok] テストは通ることを確認済み
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SampleApiInstrumentedTest {

	@Test
	public void getCoupon_ok() throws Exception {
		// Context of the app under test.
		Context appContext = InstrumentationRegistry.getTargetContext();
		assertEquals("practice.com.powermockforkii", appContext.getPackageName());

		// run
		SampleApi sampleApi = new SampleApi();
		KiiObject obj = sampleApi.getCoupon(SampleApi.targetCouponId);

		// test
		assertNotNull(obj);
		assertEquals("1024", obj.getString("couponNo", null));
		assertEquals("割引クーポン", obj.getString("title", null));
		assertEquals("ランチのみ100円割引", obj.getString("content", null));

	}
}
