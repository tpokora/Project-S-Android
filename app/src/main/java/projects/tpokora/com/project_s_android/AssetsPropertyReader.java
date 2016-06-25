package projects.tpokora.com.project_s_android;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by tomek on 25.06.16.
 */
public class AssetsPropertyReader {

    private Context context;
    private Properties properties;

    public AssetsPropertyReader(Context context) {
        this.context = context;

        properties = new Properties();
    }

    public Properties getProperties(String fileName) {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(fileName);
            properties.load(inputStream);
        } catch (IOException e) {
            Log.e(AssetsPropertyReader.this.getClass().getName(), "Could not read file");
        }

        return properties;
    }

}
