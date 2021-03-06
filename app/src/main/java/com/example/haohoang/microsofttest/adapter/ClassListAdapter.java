package com.example.haohoang.microsofttest.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haohoang.microsofttest.databases.DbClassContext;
import com.example.haohoang.microsofttest.R;
import com.example.haohoang.microsofttest.activities.StudentListActivity;
import com.example.haohoang.microsofttest.adapter.viewhodler.ClassListViewHodler;
import com.example.haohoang.microsofttest.classlistdata.ClassStudent;
import com.example.haohoang.microsofttest.databases.DbStudentContext;
import com.example.haohoang.microsofttest.sutudentdata.Student;

import org.greenrobot.eventbus.EventBus;

import java.util.Vector;

/**
 * Created by tranh on 3/6/2017.
 */

public class ClassListAdapter extends RecyclerView.Adapter<ClassListViewHodler> {
    private final String TAG = ClassListAdapter.class.toString();
    private Context context;
    private final String urlGetList = "https://westus.api.cognitive.microsoft.com/face/v1.0/persongroups?start=0";

    @Override
    public ClassListViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.class_item, parent, false);

        //2: create ViewHolder
        return new ClassListViewHodler(itemView);
    }

    public ClassListAdapter(Context context) {
        this.context = context;

    }


    @Override
    public void onBindViewHolder(ClassListViewHodler holder, int position) {
        final ClassStudent classStudent = DbClassContext.instance.getClassStudents().get(position);
        classStudent.setStudents(new Vector<Student>());
        Log.e(TAG, String.format("onBindViewHolder: GỬi bind %s", classStudent.getStudents().size()));
        holder.bind(classStudent);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(classStudent);
                Log.e(TAG, String.format("onClick: %s", classStudent.getStudents().size()));
                DbStudentContext.instance.setIdGroup(classStudent.getPersongroupid());
                DbStudentContext.instance.getAllStudentInGroup();
                Intent intent = new Intent(context, StudentListActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return DbClassContext.instance.getClassStudents().size();
    }


}
