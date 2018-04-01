package com.example.aliyya.aliyya_1202154356_modul6.homescreen;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aliyya.aliyya_1202154356_modul6.MainActivity;
import com.example.aliyya.aliyya_1202154356_modul6.model.Post;

import java.util.ArrayList;

/**
 * Created by Aliyya on 4/1/2018.
 */

public class FragmenPostTerbaru extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    ProgressDialog mProgressDialog;

    private ArrayList<Post> listPosts;
    //our database reference object
    DatabaseReference databaseFood;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_terbaru, container, false);

        databaseFood = FirebaseDatabase.getInstance().getReference(MainActivity.table1);

        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setTitle("Loading Data");
        mProgressDialog.setMessage("Please wait....");
        mProgressDialog.show();

        recyclerView = view.findViewById(R.id.recyclerView);

        listPosts = new ArrayList<>();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart(); //attaching value event listener
        databaseFood.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listPosts.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Post post = postSnapshot.getValue(Post.class);

                    listPosts.add(post);
                }
                recyclerView.setHasFixedSize(true);

                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

                PostAdapter postList = new PostAdapter(getContext(), listPosts);

                recyclerView.setAdapter(postList);
                mProgressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

