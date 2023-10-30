package com.myadhd.myadhd.Activities.Adapters;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.myadhd.MyApplication;
import com.myadhd.R;
import com.myadhd.myadhd.Activities.DiagnosticActivity;
import com.myadhd.myadhd.Activities.QuizActivity;
import com.myadhd.myadhd.Retrofit.ApiClient;
import com.myadhd.myadhd.DTOS.Child;
import com.myadhd.myadhd.Retrofit.RetrofitClient;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChildCustomAdapter extends ArrayAdapter<Child> {

    ArrayList<Child> childrenList;

    public ChildCustomAdapter(Activity context, ArrayList<Child> childrenList ){
        super(context, 0, childrenList);
        this.childrenList = childrenList;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.child_list, parent, false);
        }
        Child currentChild = getItem(position);
        TextView firstname = listItemView.findViewById(R.id.firstname);
        TextView lastname = listItemView.findViewById(R.id.lastname);
        TextView age = listItemView.findViewById(R.id.age);
        TextView gender = listItemView.findViewById(R.id.gender);
        TextView code = listItemView.findViewById(R.id.code);

        firstname.setText("Prenume: " + currentChild.getFirstname());
        lastname.setText("Nume: " + currentChild.getLastname());
        age.setText("Vârsta: " + currentChild.getAge());
        gender.setText("Gen: " + currentChild.getGender());
        code.setText("Cod: " + currentChild.getCode());

        MyApplication application = new MyApplication();
        String token = "Bearer " + application.getUserToken();

        Button testBtn = listItemView.findViewById(R.id.testBtn);
        Button deleteBtn = listItemView.findViewById(R.id.deleteBtn);
        Button resultsBtn = listItemView.findViewById(R.id.resultsBtn);
        testBtn.setOnClickListener(view -> {
            application.setCurrentChildId(currentChild.getChildId());
            application.setCurrentChildCode(currentChild.getCode());
            Intent intent = new Intent(view.getContext(), QuizActivity.class);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(intent);

        });

        deleteBtn.setOnClickListener(view -> {
            application.setCurrentChildId(currentChild.getChildId());
            application.setCurrentChildCode(currentChild.getCode());
            ApiClient apiClient = RetrofitClient.getRetrofitInstance().create(ApiClient.class);
            Call<ResponseBody> call = apiClient.deleteChildById(token, currentChild.getChildId());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.i("S-a sters! ", String.valueOf(response.body()));
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.i("Nu s-a sters! ", String.valueOf(t));
                }
            });
            childrenList.remove(currentChild);
            this.notifyDataSetChanged();
        });

        resultsBtn.setOnClickListener(view -> {
            application.setCurrentChildId(currentChild.getChildId());
            application.setCurrentChildCode(currentChild.getCode());
            Intent intent = new Intent(view.getContext(), DiagnosticActivity.class);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(intent);

        });

        return listItemView;
    }
}
