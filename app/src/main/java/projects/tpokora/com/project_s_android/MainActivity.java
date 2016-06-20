package projects.tpokora.com.project_s_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static boolean loginChecked = false;

    public EditText loginEditText;
    public EditText passwordEditText;
    public Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initiate components
        loginEditText = (EditText) findViewById(R.id.login_editText);
        passwordEditText = (EditText) findViewById(R.id.password_editText);
        showPasswordEditText(loginChecked);
        loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!loginChecked) {
                    String login = loginEditText.getText().toString();
                    if (login.equals("Tomek")) {
                        Log.d("login", "true");
                        loginChecked = true;
                        showPasswordEditText(loginChecked);
                    }
                } else {
                    Log.d("TRUE", "TRUE");
                    loginChecked = false;
                    showPasswordEditText(loginChecked);
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
}
