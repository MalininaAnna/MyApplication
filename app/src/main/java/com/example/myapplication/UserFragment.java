package com.example.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserFragment extends Fragment {
    private User user;
    private TextView userInfo;
    private EditText editName;
    private EditText editLastname;
    private EditText editPhone;
    private Button updateBtn;
    private Button deleteBtn;
    private UserList userList;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        user = (User) bundle.getSerializable("user");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_user,container,false);
        userList = UserList.get(getActivity());
        userInfo = view.findViewById(R.id.userInfo);
        editName = view.findViewById(R.id.editName);
        editLastname = view.findViewById(R.id.editLastname);
        editPhone = view.findViewById(R.id.editPhone);
        updateBtn = view.findViewById(R.id.updateBtn);
        deleteBtn = view.findViewById(R.id.deleteBtn);
        final String userName = user.getUserLastName()+" "+user.getUserName()+"\n";
        userInfo.setText(userName);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newUserName = editName.getText().toString();
                String newUserLastname = editLastname.getText().toString();
                String newUserPhone = editPhone.getText().toString();
                user.setUserName(newUserName);
                user.setUserLastName(newUserLastname);
                user.setPhone(newUserPhone);
                userList.updateUser(user);
                Toast.makeText(getActivity(),"Данные изменены",Toast.LENGTH_SHORT).show();
            }

        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userList.deleteUser(null);
                Toast.makeText(getActivity(), "Данные удаленны", Toast.LENGTH_SHORT).show();
                //Intent intent = new Intent(UserFragment.this, UserListFragment.class);
                //startActivity(intent);
                //itemView - это элемент списка
                //deleteUser = database.delete(UserDbSchema.UserTable.NAME, null,null);
                }
        });
        return view;
    }
}
