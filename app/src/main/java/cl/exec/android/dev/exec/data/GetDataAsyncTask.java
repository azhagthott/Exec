package cl.exec.android.dev.exec.data;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.String;

/**
 * Created by fran on 04-05-15.
 */
public class GetDataAsyncTask extends AsyncTask<String, Void, String[]> {

    private final String LOG_TAG = GetDataAsyncTask.class.getSimpleName();
    private static final String URL_JSON_BUG_PROJECT = "http://alvaro.desa.exec.cl/moe/json_output.php?query=bug_project";

    @Override
    protected String[] doInBackground(String... params) {

        ServicesHandler servicesHandler = new ServicesHandler();
        servicesHandler.makeServiceCall(URL_JSON_BUG_PROJECT, 2);
        String response = servicesHandler.makeServiceCall(URL_JSON_BUG_PROJECT, 2);

        try {
            JSONArray jsonArray = new JSONArray(response);

            for (int i = 0; i < jsonArray.length(); i++) {

                Log.d(LOG_TAG, "i::::" + i);

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Integer idProject = jsonObject.getInt("id");
                String nameProject = jsonObject.getString("name");
                String descriptionProject = jsonObject.getString("description");
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    protected void onPostExecute(String[] result) {

    }

}
