package tn.esprit.roomdbproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.roomdbproject.database.AppDataBase;
import tn.esprit.roomdbproject.entity.User;

public class MainActivity extends AppCompatActivity {


    private EditText mFirstName, mLastName;
    private Button addButton;
    private AppDataBase dataBase;
    private UserAdapter userAdapter;
    private List<User> userList = new ArrayList<User>();
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerspace);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mFirstName = findViewById(R.id.firstname);
        mLastName = findViewById(R.id.lastname);
        addButton = findViewById(R.id.add);

        dataBase = AppDataBase.getAppDatabase(this);
        userList = dataBase.userDao().getAll();

        userAdapter = new UserAdapter(userList, this);
        recyclerView.setAdapter(userAdapter);



        addButton.setOnClickListener(v -> {
            String firstName = mFirstName.getText().toString().trim();
            String lastName = mLastName.getText().toString().trim();

            if (!firstName.isEmpty() && !lastName.isEmpty()) {
                User user = new User();
                user.setFristName(firstName);
                user.setLastName(lastName);

                dataBase.userDao().inserOne(user);
                notifyChange(dataBase.userDao().getAll());

                mFirstName.setText("");
                mLastName.setText("");
            }
        });
    }
    private void notifyChange(List<User> users) {
        userList.clear();
        userList.addAll(users);
        userAdapter.notifyDataSetChanged();
    }
}