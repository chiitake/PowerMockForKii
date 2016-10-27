# PowerMockForKii
**目的：　KiiCould Android SDKをPowerMockをつかってテストする**  

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
  **現在エラーがでる**  
  ----  
  
**■テストクラス**  
**SampleApiCalcMockTest**  
PowerMockの動作確認用。staticクラスもspy動作確認済み

**■現状の問題点**  
**SampleApiGetCouponMockTest**  
２つのテストを行いたいが以下のエラーが出て、うまくテストできない。


java.lang.IllegalStateException: Failed to transform class with name com.kii.cloud.storage.Kii. Reason: cannot find org.apache.http.client.methods.HttpGet

	at org.powermock.core.classloader.MockClassLoader.loadMockClass(MockClassLoader.java:283)
	at org.powermock.core.classloader.MockClassLoader.loadModifiedClass(MockClassLoader.java:192)
	at org.powermock.core.classloader.DeferSupportingClassLoader.loadClass(DeferSupportingClassLoader.java:71)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:348)
	at sun.reflect.generics.factory.CoreReflectionFactory.makeNamedType(CoreReflectionFactory.java:114)
	at sun.reflect.generics.visitor.Reifier.visitClassTypeSignature(Reifier.java:125)
	at sun.reflect.generics.tree.ClassTypeSignature.accept(ClassTypeSignature.java:49)
	at sun.reflect.annotation.AnnotationParser.parseSig(AnnotationParser.java:439)
	at sun.reflect.annotation.AnnotationParser.parseClassValue(AnnotationParser.java:420)
	at sun.reflect.annotation.AnnotationParser.parseClassArray(AnnotationParser.java:724)
	at sun.reflect.annotation.AnnotationParser.parseArray(AnnotationParser.java:531)
	at sun.reflect.annotation.AnnotationParser.parseMemberValue(AnnotationParser.java:355)
	at sun.reflect.annotation.AnnotationParser.parseAnnotation2(AnnotationParser.java:286)
	at sun.reflect.annotation.AnnotationParser.parseAnnotations2(AnnotationParser.java:120)
	at sun.reflect.annotation.AnnotationParser.parseAnnotations(AnnotationParser.java:72)
	at java.lang.Class.createAnnotationData(Class.java:3521)
	at java.lang.Class.annotationData(Class.java:3510)
	at java.lang.Class.getAnnotation(Class.java:3415)
	at org.junit.internal.MethodSorter.getDeclaredMethods(MethodSorter.java:52)
	at org.junit.internal.runners.TestClass.getAnnotatedMethods(TestClass.java:45)
	at org.junit.internal.runners.MethodValidator.validateTestMethods(MethodValidator.java:71)
	at org.junit.internal.runners.MethodValidator.validateStaticMethods(MethodValidator.java:44)
	at org.junit.internal.runners.MethodValidator.validateMethodsForDefaultRunner(MethodValidator.java:50)
	at org.powermock.modules.junit4.internal.impl.PowerMockJUnit44RunnerDelegateImpl.validate(PowerMockJUnit44RunnerDelegateImpl.java:108)
	at org.powermock.modules.junit4.internal.impl.PowerMockJUnit44RunnerDelegateImpl.<init>(PowerMockJUnit44RunnerDelegateImpl.java:70)
	at org.powermock.modules.junit4.internal.impl.PowerMockJUnit47RunnerDelegateImpl.<init>(PowerMockJUnit47RunnerDelegateImpl.java:42)
	at org.powermock.modules.junit4.internal.impl.PowerMockJUnit49RunnerDelegateImpl.<init>(PowerMockJUnit49RunnerDelegateImpl.java:25)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at org.powermock.modules.junit4.common.internal.impl.JUnit4TestSuiteChunkerImpl.createDelegatorFromClassloader(JUnit4TestSuiteChunkerImpl.java:172)
	at org.powermock.modules.junit4.common.internal.impl.JUnit4TestSuiteChunkerImpl.createDelegatorFromClassloader(JUnit4TestSuiteChunkerImpl.java:48)
	at org.powermock.tests.utils.impl.AbstractTestSuiteChunkerImpl.createTestDelegators(AbstractTestSuiteChunkerImpl.java:113)
	at org.powermock.modules.junit4.common.internal.impl.JUnit4TestSuiteChunkerImpl.<init>(JUnit4TestSuiteChunkerImpl.java:71)
	at org.powermock.modules.junit4.common.internal.impl.AbstractCommonPowerMockRunner.<init>(AbstractCommonPowerMockRunner.java:32)
	at org.powermock.modules.junit4.PowerMockRunner.<init>(PowerMockRunner.java:34)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at org.junit.internal.builders.AnnotatedBuilder.buildRunner(AnnotatedBuilder.java:104)
	at org.junit.internal.builders.AnnotatedBuilder.runnerForClass(AnnotatedBuilder.java:86)
	at org.junit.runners.model.RunnerBuilder.safeRunnerForClass(RunnerBuilder.java:59)
	at org.junit.internal.builders.AllDefaultPossibilitiesBuilder.runnerForClass(AllDefaultPossibilitiesBuilder.java:26)
	at org.junit.runners.model.RunnerBuilder.safeRunnerForClass(RunnerBuilder.java:59)
	at org.junit.internal.requests.ClassRequest.getRunner(ClassRequest.java:33)
	at org.junit.internal.requests.FilterRequest.getRunner(FilterRequest.java:36)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:98)
	at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:42)
	at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:234)
	at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:74)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
Caused by: javassist.CannotCompileException: cannot find org.apache.http.client.methods.HttpGet
	at javassist.expr.NewExpr.replace(NewExpr.java:215)
	at org.powermock.core.transformers.impl.AbstractMainMockTransformer$PowerMockExpressionEditor.edit(AbstractMainMockTransformer.java:436)
	at javassist.expr.ExprEditor.loopBody(ExprEditor.java:212)
	at javassist.expr.ExprEditor.doit(ExprEditor.java:91)
	at javassist.CtClassType.instrument(CtClassType.java:1437)
	at org.powermock.core.transformers.impl.ClassMockTransformer.transformMockClass(ClassMockTransformer.java:65)
	at org.powermock.core.transformers.impl.AbstractMainMockTransformer.transform(AbstractMainMockTransformer.java:247)
	at org.powermock.core.classloader.MockClassLoader.loadMockClass(MockClassLoader.java:264)
	... 58 more
Caused by: javassist.NotFoundException: org.apache.http.client.methods.HttpGet
	at javassist.ClassPool.get(ClassPool.java:452)
	at javassist.expr.NewExpr.replace(NewExpr.java:189)
	... 65 more


Process finished with exit code -1
