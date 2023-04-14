# ReactNativeExpo(Bare workflow)の環境構築・新規アプリの作成手順

## 前準備
- 最新のAndroidStudioをインストールする  
    [\\\\192.168.0.34\Common\Tools\PC初期設定\10_android-studio](\\192.168.0.34\Common\Tools\PC初期設定\10_android-studio)からインストーラーを取得してください。  
- 実機デバックする場合は認証情報をリセットしておく ※npx run androidコマンド実行時にエラーになることがあるので
    1. 実機とPCのUSB接続を外しておく
    2. 設定アプリ→システム→開発者向けオプション→USBデバックの許可の取り消しをクリック
    3. 再度PCと実機をUSB接続する
    4. 実機側に接続確認のダイアログが表示されることを確認。
    5. OKボタン押下してダイアログを閉じる
- ANDROID_HOMEの環境変数を追加する
    1. Windowsボタンを右クリック→システム→システムの詳細設定
    2. システムプロパティ画面→環境変数 を開く
    3. xxxxxのユーザ環境変数の新規ボタンをクリック
    4. 変数名にANDROID_HOME、変数値にC:\Users\<ユーザ名>\AppData\Local\Android\Sdkを設定
        ※念のため変数値に設定するパスが存在するか確認する。そのパス配下にplatform-toolsが存在していればOK
- adbコマンドへのパスを通しておく
    1. ANDROID_HOMEの環境変数を追加した画面で変数名Pathをダブルクリックする
    2. リストの下に'%ANDROID_HOME%\platform-tools'を追加する
- voltaでnode.jsをインストールしていない場合はPCからnode.jsを削除する
	1. コマンドプロンプトでnpm cache clean --force
	2. コンパネからNode.jsをuninstall
	3. PCを再起動する
	4. 以下のフォルダが存在しているか確認し、存在していれば削除する

		・C:\Program Files (x86)\Nodejs
		・C:\Program Files\Nodejs
		・C:\Users\{User}\AppData\Roaming\npm (or %appdata%\npm)
		・C:\Users\{User}\AppData\Roaming\npm-cache (or %appdata%\npm-cache)
		・C:\Users\{User}\.npmrc (and possibly check for that without the . prefix too)
		・C:\Users\{User}\AppData\Local\Temp\npm-*

	5. コマンドプロンプトでecho %PATH%してNodejsまたはnpmを含むパスが存在しているか確認する
	6. コマンドプロンプトでwhere nodeを実行し、nodeが見つからないことを確認。存在する場合はその親ディレクトリごと削除
	7. PCを再起動する

    ※参考
	https://stackoverflow.com/questions/20711240/how-to-completely-remove-node-js-from-windows
- voltaとnpm、node.jsをインストールする
    1. 以下からインストーラーをダウンロードし実行する  
        https://docs.volta.sh/guide/getting-started
    2. コマンドプロンプトを開いて下記コマンドを実行しvoltaがインストールされていることを確認する
        ```
        > volta --version
        ```
    3. nodeとnpmをインストールする
        ```
        > volta install node@19.9.0
        > volta install npm@9.6.4
        ```

## 新規アプリの作成手順 (プロジェクトファイル作成～実機デバックまで)

    1. Android端末とPCをUSB接続しておく(WiFiデバックでも可)
    2. コマンドプロンプトで以下のコマンドを実行する
    ```
    > npx create-expo-app -t bare-minimum <任意のプロジェクト名>
    > cd <任意のプロジェクト名>
    > npx run android
    ```
    3. npx run androidの終了をしばらく待つ。
    4. ビルドが成功したことを確認する
        ビルド成功時はコマンドプロンプトに以下が出力されます。
        ```
        > npx run android
        ・・・
		BUILD SUCCESSFUL in 6m 38s
		203 actionable tasks: 148 executed, 50 from cache, 5 up-to-date
		Starting Metro Bundler
		▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄
		█ ▄▄▄▄▄ █▄▀▀▄▄▀▀█▄█ ▄▄▄▄▄ █
		█ █   █ ███▄█  ▀▀▄█ █   █ █
		█ █▄▄▄█ ██▄▀▄▀███▀█ █▄▄▄█ █
		█▄▄▄▄▄▄▄█ █ ▀▄▀ █ █▄▄▄▄▄▄▄█
		█ ▄▀▄▄▀▄█▀ ▄▄▀▀█ ██▄▀▀▀▀▀▄█
		█▀▀▀▀▀█▄▄▀▀  ▀█▄  ▄▄ ▀ ▀▀ █
		█▀ ▄█▀ ▄▀▄▄█▄▄▀▄ ▀█▄▄▀██▀▄█
		█ ▄▄   ▄▀▀██ ▄▄█ █▀  █▄ ▄ █
		█▄████▄▄█ █ ▀▀▀▄█ ▄▄▄ █▄ ██
		█ ▄▄▄▄▄ ██ █▄▀█▀▀ █▄█ ▄█▀ █
		█ █   █ █ █▄█▄▄ ▄   ▄▄ █  █
		█ █▄▄▄█ █▀▀▀█▄█ ▄▄▄█  █   █
		█▄▄▄▄▄▄▄█▄▄███▄▄█▄▄▄██▄██▄█

		› Metro waiting on http://localhost:8081
		› Scan the QR code above with Expo Go (Android) or the Camera app (iOS)

		› Press a │ open Android
		› Press w │ open web

		› Press j │ open debugger
		› Press r │ reload app
		› Press m │ toggle menu

		› Press ? │ show all commands
        ```
    5. <プロジェクトフォルダ>/App.js内の文字列などを変更して実機に変更が即座に反映されることを確認する

## APKの自動更新、NFC対応手順

    ReactNativeExpoでAPKのインストールやNFC機能を利用するためには、<プロジェクトフォルダ>/android直下のファイルを編集する必要があります。  
    以下の手順を実施し環境構築を行ってください。

    1. <プロジェクトフォルダ>/android/app/src/main/javaにchinosoftexフォルダを作成する
    2. NativeHelperModule.java、NativeHelperPackage.javaをchinosoftexフォルダに保存する
    3. <プロジェクトフォルダ>/android/app/src/main/java/・・・/MainApplication.javaに以下の変更を加える
        ```
        ・・・
        import chinosoftex.NativeHelperPackage; ★★★ この行を追加
        ・・・
        public class MainApplication extends Application implements ReactApplication {
        ・・・
        @Override
        protected List<ReactPackage> getPackages() {
            @SuppressWarnings("UnnecessaryLocalVariable")
            List<ReactPackage> packages = new PackageList(this).getPackages();
            // Packages that cannot be autolinked yet can be added manually here, for example:
            packages.add(new NativeHelperPackage()); ★★★ この行を追加
            return packages;
        }
        ```
    4. <プロジェクトフォルダ>等任意の場所にNativeHelper.jsを保存します。
    5. AndroidManifest.xmlに以下を追記する
        ```
        <manifest xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:tools="http://schemas.android.com/tools"> ★★★ xmlns:toolsを追加
        ・・・
        <uses-permission android:name="android.permission.NFC"/> ★★★ 追加
        <uses-permission android:name="android.permission.ADD_SYSTEM_SERVICE" tools:ignore="ProtectedPermissions" /> ★★★ 追加
        <uses-permission android:name="android.permission.FOREGROUND_SERVICE" /> ★★★ 追加
        <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES"/> ★★★ 追加
        <uses-permission android:name="android.permission.REQUEST_DELETE_PACKAGES" /> ★★★ 追加
        <uses-permission android:name="android.permission.INSTALL_PACKAGES" tools:ignore="ProtectedPermissions" /> ★★★ 追加
        ・・・
        <provider ★★★ 追加
            android:name="androidx.core.content.FileProvider"
            android:authorities="com.mybare.fileprovider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_paths" />
        </provider>
        ```
    6. app/build.gradleに以下を追記する
        ```
        ・・・
        dependencies {
            implementation 'androidx.core:core-ktx:1.5.0' ★★★ 追加
        ・・・
        ```

    7. res/xml/file_paths.xmlを新規作成する
        ※files-pathのname属性はアプリによって変更してください
        ```
        <paths xmlns:android="http://schemas.android.com/apk/res/android">
            <files-path path="/" name="mybare.apk" />
        </paths>
        ```

    8. コマンドプロンプトで以下のコマンドを実行し、ビルドを行う
        ```
        > npx expo run:android
        ```

    - 補足情報
        ※本手順で使用使用可能になるNativeHelper.jsは以下のように使用します。
        ```
        import NativeHelper from './NativeHelper';
        ・・・
        const filesDir = await NativeHelper.getFilesDir();
        console.log('getFilesDir:' + filesDir);

        const uri = await NativeHelper.getUriForFile("com.mybare.fileprovider", apkPath);
        console.log('getUriForFile:' + uri);
        ```

## その他アプリ開発で必要になるパッケージ一覧

    ReactNativeExpoでアプリ開発をする際にインストールが必要となるパッケージ一覧です。

    - UIデザインライブラリ (react-native-papper)
        ```
        > npm install react-native-paper
        ```
    - ステート管理用ライブラリ (redux)
        ```
        > npm install redux
        ```
    - バーコードスキャン用ライブラリ (expo-barcode-scanner)
        ```
        > npm expo install expo-barcode-scanner
        ```
    - [Android用] startActivity()をJS側から呼び出すライブラリ (expo-intent-launcher) ※APK更新で必要
        ```
        > npx expo install expo-intent-launcher
        ```
    - ファイルダウンロード用ライブラリ (expo-file-system) ※APK更新で必要
        ```
        > npx expo install expo-file-system
        ```

## トラブルシューティング

    - npx expo run:android時にRNCSafeAreaProviderエラーが発生する場合は以下リンクの2021/1/5のsobhanberaさんの投稿を参照してください。
        https://github.com/react-navigation/react-navigation/issues/8964

## APKの作成手順

    1. 事前に以下のサイトでアカウントを作成しておく
        https://expo.dev/
    2. 必要なパッケージをインストールしておく
        ```
        > npm install expo-cli eas-cli
        ```
    3. 以下コマンドでexpoにログインしておく
        ```
        > npx expo login
        √ Email or username ... xxxxx@chinosoftex.co.jp ※1で作成したアカウント名を入力
        √ Password ... ***********                      ※1で作成したアカウントのパスワードを入力
        ```
    4. 以下コマンドで正常にログインできたかを確認する
        ```
        > npx expo whoami
        xxxxxx ※正しくログインができれいればユーザIDが表示される
        ```
    5. eas buildでapkをビルドする
        ```
        > npx eas build -p android --profile preview
        ・・・・
        √ Would you like to automatically create an EAS project for  ・・・ ※yを入力
        ・・・・
        √ Generate a new Android Keystore? ... ※nを入力
        ・・・
        √ Path to the Keystore file. ...           ※.jksファイルのパスを指定
        √ Keystore password ... *****************  ※.jksを作成したときにパスワードを入力
        ? Key alias » fuzokuhinlabelhakkou          ※.jksを作成したときのaliasを入力
        √ Key password ... *****************       ※.jksを作成したときにパスワードを入力
        √ Created keystore
        ・・・
        🤖 Android app:
        https://expo.dev/artifacts/eas/bU4ZMDkwMaEwJyKkoAPQVj.apk ★★★ apkのダウンロードリンクが表示されたらok
        ```
