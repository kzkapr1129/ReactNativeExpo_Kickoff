package chinosoftex;

import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;

import org.jetbrains.annotations.NotNull;

public class NativeHelperModule extends ReactContextBaseJavaModule {

    public NativeHelperModule (@Nullable ReactApplicationContext reactContext){
        super(reactContext);
    }

    @Override
    public String getName() {
        return "NativeHelper";
    }

    @ReactMethod
    public void getFilesDir(Promise promise) {
        String absolutePath = getReactApplicationContext().getFilesDir().getAbsolutePath();
        promise.resolve(absolutePath);
    }

    @ReactMethod
    public void getUriForFile(String authority, String filepath, Promise promise) {
        java.io.File file = new java.io.File(filepath);
        android.net.Uri apkUri = androidx.core.content.FileProvider.getUriForFile(
            getReactApplicationContext(),
            authority,
            file
        );
        promise.resolve(apkUri.toString());
    }
}
