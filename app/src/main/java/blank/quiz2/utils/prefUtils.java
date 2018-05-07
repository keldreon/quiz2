package blank.quiz2.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import blank.quiz2.activity.login;

public class prefUtils {
    public prefUtils(){}

    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "Quiz2Pref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    //public static final String KEY_NAME = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_ID = "id";
    public static final String KEY_PASS = "password";
    public static final String KEY_SALDO = "saldo";
    //public static final String KEY_JENISUSER = "status";

    private boolean login;

    public prefUtils(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String email, String pass, String saldo){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        // Storing name in pref
        editor.putString(KEY_ID, email);
        // Storing email in pref
        editor.putString(KEY_PASS, pass);
        // Storing status in pref
        editor.putString(KEY_SALDO, saldo);
        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, login.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }
    /**
     * Get stored session data
     * */
    /*public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_N, pref.getString(KEY_NAME, null));
        // user email
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        // user status
        user.put(KEY_JENISUSER, pref.getString(KEY_JENISUSER, null));
        // return user
        return user;
    }*/

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, login.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     **/
    // Get Login State
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getNama(){
        String baru = pref.getString("id","");
        return baru;
    }

    public String getPass(){
        String baru = pref.getString("password","");
        return baru;
    }

    public String getSaldo(){
        String baru = pref.getString("saldo","");
        return baru;
    }
}
