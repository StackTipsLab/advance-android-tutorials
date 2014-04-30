package com.javatechig.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddTodoActivity extends Activity implements OnClickListener {
    private Button addTodoBtn;
    private SQLController dbController;
	private EditText subjectEditText;
	private EditText descEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setTitle("Add Record");
        
        setContentView(R.layout.activity_add_record);
        subjectEditText = (EditText) findViewById(R.id.subject_edittext);
        descEditText = (EditText) findViewById(R.id.description_edittext);
        
        addTodoBtn = (Button) findViewById(R.id.add_record);

        dbController = new SQLController(this);
        dbController.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.add_record:
            final String name = subjectEditText.getText().toString();
            final String desc = descEditText.getText().toString();
            dbController.insert(name, desc);
            
            Intent main = new Intent(AddTodoActivity.this, TodoListActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(main);
            break;
        default:
            break;
        }
    }

}