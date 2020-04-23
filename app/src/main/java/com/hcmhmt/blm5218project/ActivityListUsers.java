package com.hcmhmt.blm5218project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActivityListUsers extends AppCompatActivity {

    private RecyclerView rc;
    ArrayList<Users> userList = new ArrayList<Users>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);

        rc = (RecyclerView) findViewById(R.id.rw_listuser);

        setUserArrayList();
        
        UserListAdapter userAdapter = new UserListAdapter(this,userList);

        rc.setAdapter(userAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rc.setLayoutManager(linearLayoutManager);

    }

    private void setUserArrayList() {

        int productImages[] = {
                 R.drawable.bilbo    ,R.drawable.frodo   ,R.drawable.boromir   ,R.drawable.faramir
                ,R.drawable.aragorn  ,R.drawable.gandalf ,R.drawable.legolas   ,R.drawable.gimli
                ,R.drawable.saorun   ,R.drawable.saruman ,R.drawable.theoden   ,R.drawable.eomer
                ,R.drawable.eowyn    ,R.drawable.sam     ,R.drawable.galadriel ,R.drawable.smaug
                ,R.drawable.denethor ,R.drawable.beorn   ,R.drawable.gollum   , R.drawable.elrond
        };
        String[] userNames = {
                 "BilboBaggins" ,"FrodoBaggins"  ,"Boromir"  ,"Faramir"
                ,"Aragorn"      ,"Gandalf"       ,"Legolas"  ,"Gimli"
                ,"Sauron"       ,"Saruman"       ,"Theoden"  ,"Eomer"
                ,"Eowyn"        ,"Sam"           ,"Galadriel","Smaug"
                ,"Denethor"     ,"Beorn"         ,"Gollum"   ,"Elrond"};
        String[] userPasswords = {
                 "Bilbo123"   , "Frodo123" ,"Boromir123"  ,"Faramir123"
                ,"Aragorn123" ,"Gandalf123","Legolas123"  ,"Gimli123"
                ,"Sauron123"  ,"Saruman123","Theoden123"  ,"Eomer123"
                ,"Eowyn123"   ,"Sam123"    ,"Galadriel123","Smaug123"
                ,"Denethor123","Beorn123"  ,"Gollum123"   ,"Elrond123"};

        for(int i = 0 ; i<userNames.length; i++){
            Users temp = new Users();

            temp.setUsername(userNames[i]);
            temp.setPassword(userPasswords[i]);
            temp.setImage(productImages[i]);

            userList.add(temp);
        }



    }
}
