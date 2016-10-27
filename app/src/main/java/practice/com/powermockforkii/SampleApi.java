package practice.com.powermockforkii;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.kii.cloud.storage.Kii;
import com.kii.cloud.storage.KiiBucket;
import com.kii.cloud.storage.KiiObject;
import com.kii.cloud.storage.exception.app.AppException;

import java.io.IOException;

/**
 * Created by natty on 2016/10/27.
 */

public class SampleApi {
	// 事前に保存したクーポンObjectID "coupon_list" : object id
	public static final String targetCouponId = "e2353eb0-9bf2-11e6-8188-22000a66c675";
	/**
	 * アプリケーションスコープ: バケット: "coupon_list"に
	 * KiiCloudのDataBrowserでオブジェクトを追加
	 *
	 * _id: e2353eb0-9bf2-11e6-8188-22000a66c675
	 *
	 * {
	 *  "couponNo": "1024",
	 *  "title": "割引クーポン",
	 *  "content": "ランチのみ00円割引"
	 * }
	 */

	/**
	 * アプリケーションスコープ: バケット: "coupon_list"
	 * ユーザースコープのサーバーからクーポン情報を取得する [test]
	 *
	 * @param couponId 呼ばれる前に Nullではないことが保証されている
	 * @return クーポンobject
	 * @exception NullPointerException, IOException, AppException エラーがあった場合は各Exceptionを発生させる
	 */
	public KiiObject getCoupon(@NonNull String couponId) throws NullPointerException, IOException, AppException {
		if (TextUtils.isEmpty(couponId)) {
			throw new NullPointerException("couponIdが空です");
		}

		// バケットの取得
//		KiiBucket kiiBucket = Kii.user().bucket("coupon_list");	// UserScope
		KiiBucket kiiBucket = Kii.bucket("coupon_list");	// AppScope
		if (kiiBucket == null) {
			throw new NullPointerException("KiiBucketが存在しない");
		}

		// クーポンKiiオブジェクトの取得
		KiiObject objCoupon = kiiBucket.object(couponId);    // クーポンIDで取得
		objCoupon.refresh();    // 最新情報をサーバーから取得
		return objCoupon;
	}

	/**
	 * 単純なテスト
	 * @param a
	 * @param b
	 * @return
	 */
	public int sum(int a, int b) {
		return Calc.sum(a, b);
		// return a + b;
	}
}
