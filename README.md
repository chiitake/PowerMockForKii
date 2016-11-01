# PowerMockForKii
**目的：　KiiCould Android SDKをPowerMockをつかってテストする**  

** すべてのテストが通るようになりました。感謝 nori様(Kiiスタッフ)
<http://community-jp.kii.com/t/junit4-kii/598/6>


**■環境**  
Android 4.0以降  
MultiIndexを使用  

**■事前準備　テストデータを入力**  
まずMyApplicationクラスに、テスト用のKiiサーバーのAppId, AppKeyを記述する。  

----
KiiCouldサーバーのDataBrowserから  
**アプリケーションスコープ**で**バケット"coupon_list"を作成  **
その中に、オブジェクトを1つ作成  
{  
  "couponNo": "1024",  
  "title": "割引クーポン",  
  "content": "ランチのみ100円割引"  
}  

保存後、オブジェクトの "_id" を、**SampleApi.targetCouponId に転記**しておく  
----  

**■テストの実行
  ##■テスト1 実際にAndroid上でKiiを動かしてデータを取得するテスト** 
  
  SampleApiInstrumentedTestをAndroidJUnit4で実行する。
  動作確認済み。
  ----  
  ##■テスト２ ローカルのJVMで単純なクラスをPowerMockにてテストを行う

  SampleApiCalcMockTestをPowerMockRunnerで実行する。
  動作確認済み。
  ----  
  
  ##■テスト３ ローカルのJVMでPowerMockを使ってにて、テストを行う
  
  SampleApiCalcMockTestをPowerMockRunnerで実行する。
  動作確認済み。
  ----

**■テストクラス**  
**SampleApiCalcMockTest**  
PowerMockの動作確認用。staticクラスもspy動作確認済み
