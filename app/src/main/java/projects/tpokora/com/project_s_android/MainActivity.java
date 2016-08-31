package projects.tpokora.com.project_s_android;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    private static final String USERS_PROPERTIES_FILE = "users.properties";

    private Context context;
    private static HashMap users = new HashMap<String, String>();

    public static boolean loginChecked = false;

    public EditText loginEditText;
    public EditText passwordEditText;
    public Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get users
        context = this;
        users = readUsersFromProperties(context);

        // Initiate components
        loginEditText = (EditText) findViewById(R.id.login_editText);
        passwordEditText = (EditText) findViewById(R.id.password_editText);
        //showPasswordEditText(loginChecked);
        loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!loginChecked) {
                    String login = loginEditText.getText().toString();
                    if (userExists(login)) {
                        showPasswordEditText(true);
                        if (checkUserPassword(login, passwordEditText.getText().toString())) {
                            Log.d("LOGIN", "User: " + login + " password is correct");
                            loginChecked = true;
                            Intent intent = new Intent(context, ArticleActivity.class);
                            startActivity(intent);
                        } else {
                            Log.e("LOGIN", "Password incorrect!");
                            Toast.makeText(context, "Password incorrect!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("LOGIN", "User not exists");
                        Toast.makeText(context, "User not exists!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("TRUE", "TRUE");
                    loginChecked = false;
                    //showPasswordEditText(loginChecked);
                }
            }
        });
    }

    /**
     * Method to set password edit text field visible/invisible
     * @param show
     */
    private void showPasswordEditText(boolean show) {
        if (show) {
            passwordEditText.setVisibility(View.VISIBLE);
        } else {
            passwordEditText.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Check if user exists in users.properties file
     * @param name
     * @return
     */
    private boolean userExists(String name) {
        boolean exists = false;

        for (Object key : users.keySet()) {
            if (key.toString().equals(name)) {
                exists = true;
                break;
            }
        }

        return exists;
    }

    /**
     * Check users password from users.properties file is correct
     * @param login
     * @param password
     * @return
     */
    private boolean checkUserPassword(String login, String password) {
        return users.get(login).equals(password);
    }

    /**
     * Read users.properties file
     * @param context
     * @return
     */
    private HashMap<String, String> readUsersFromProperties(Context context) {
        AssetsPropertyReader assetsPropertyReader = new AssetsPropertyReader(context);
        Properties properties = assetsPropertyReader.getProperties(USERS_PROPERTIES_FILE);
        for (final Map.Entry<Object, Object> entry : properties.entrySet()) {
            users.put(entry.getKey(), entry.getValue());
        }

        return users;
    }
}
