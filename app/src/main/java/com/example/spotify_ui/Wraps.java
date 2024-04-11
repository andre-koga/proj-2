package com.example.spotify_ui;

import static androidx.core.content.ContextCompat.startActivity;
import static com.example.spotify_ui.Visibility.YOU;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.spotify_ui.ui.home.HomeFragment;

import java.util.ArrayList;

public class Wraps extends Activity {
    public static ArrayList<Wraps> wrap_list = new ArrayList<>(0);
    private final Visibility visible;

    private final String user;

    private final String wrap_name;
    private final String artists;

    public Wraps(Visibility visible, String user, String wrap_name, String artists) {
        this.visible = visible;
        this.user = user;
        this.wrap_name = wrap_name;
        this.artists = artists;
    }

    public Visibility getVisible() {
        return visible;
    }

    public String getUser() {
        return user;
    }

    public String getWrap_name() {
        return wrap_name;
    }

    public String getArtists() {
        return artists;
    }



    public void createWidget(LinearLayout main, Wraps wrap, Fragment frag) {
        View view = LayoutInflater.from(main.getContext()).inflate(R.layout.wrap_widget,null, false);
        Button btn = (Button) ((ViewGroup) view).getChildAt(0);
        View details = ((ViewGroup) view).getChildAt(1);

        TextView likes_count = (TextView) ((ViewGroup) details).getChildAt(0);
        TextView username = (TextView) ((ViewGroup) details).getChildAt(1);

//        View linear = ((ViewGroup) details).getChildAt(3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.navigation_wrap_page);
//                NavHostFragment.findNavController(new WrapPage()).navigate(R.id.navigation_wrap_page);
                Fragment fragment = new WrapPage();
                FragmentManager fragmentManager = frag.getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack("returnable");
                fragmentTransaction.commit();
            }
        });
//
//        instructor.setText("Instructor: " + (wrap.getArtists()));
//        location.setText("Location: " + wrap.getUser());
//        time.setText("Date/Time: " + wrap.getWrap_name());

        main.addView(view);
    }
}
