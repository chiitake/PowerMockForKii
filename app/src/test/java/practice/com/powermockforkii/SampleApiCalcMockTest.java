package practice.com.powermockforkii;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * 単純な計算をテストする
 * 通信を行わないで、モックテストをする
 *
 * [設定]
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(PowerMockRunner.class) // 1
@Config(packageName = "me.shopnews.app", constants = BuildConfig.class, sdk = 21)
@PowerMockIgnore({"org.mockito.*", "org.robolectric.*", "android.*"}) // 3
@PrepareForTest({SampleApi.class, Calc.class})
public class SampleApiCalcMockTest {

	/**
	 * [テスト動作確認済み]
	 * sampleApi.sum()をspyして、
	 * calc(-1, -1)を渡すとExceptionが発生することを確認
	 * それ以外は、通常の結果を返す
	 *
	 * @throws Exception
	 */
	@Test (expected = IOException.class)
	public void sampleSum_エラー確認() throws Exception {
		// spyする
		SampleApi orgSampleApi = new SampleApi();
		SampleApi spy = PowerMockito.spy(orgSampleApi);

		// setup
		// -1, -1を呼び出したら、Exceptionを発生させる
		Mockito.when(spy.sum(-1, -1)).thenThrow(IOException.class);

		// test1
		assertEquals(6, spy.sum(3,3));

		// test2
		int sum = spy.sum(-1, -1);
	}

	/**
	 * sampleApi.sum()内の、Calc.sum(-1, -1)が呼ばれたら
	 * IOExceptionが発生することを確認
	 * それ以外は、通常の結果を返す
	 * [単純なテスト] を呼び出したら、Exceptionが発生することを確認
	 * @throws Exception
	 */
	@Test (expected = IOException.class)
	public void sampleSum_staticエラー確認() throws Exception {
		// setup

		// SampleApi内のCalc.sum(-2, -2)を呼び出したら、Exceptionを発生させる
		PowerMockito.mockStatic(Calc.class);
		PowerMockito.when(Calc.sum(3, 3)).thenReturn(6);
		PowerMockito.when(Calc.sum(-2, -2)).thenThrow(IOException.class);
		PowerMockito.when(Calc.sum(-1, -1)).thenThrow(NullPointerException.class);
//		Mockito.when(spy.sum(-1, -1)).thenThrow(IOException.class);

		// spyする
		SampleApi orgSampleApi = new SampleApi();
		SampleApi spy = PowerMockito.spy(orgSampleApi);

		// -1, -1を呼び出したら、Exceptionを発生させる
		// PowerMockito.doThrow(new IOException("引数エラー")).when(spy.sum(-1, -1));
		// Mockito.when(spy.sum(anyInt(), anyInt())).thenThrow(IOException.class);


		// test1
		assertEquals("６になる", 6, spy.sum(3,3));

		// test2
		 int sum = spy.sum(-2, -2);		// IOE
		// int sum = spy.sum(-1, -1);	// NPE

	}
}