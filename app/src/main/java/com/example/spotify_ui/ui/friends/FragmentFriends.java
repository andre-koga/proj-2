
package com.example.spotify_ui.ui.friends;

import static com.example.spotify_ui.Visibility.YOU;
import static com.example.spotify_ui.Wraps.wrap_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.spotify_ui.Content;
import com.example.spotify_ui.R;
import com.example.spotify_ui.Wraps;
import com.example.spotify_ui.databinding.FragmentFriendsBinding;

public class FragmentFriends extends Fragment {

    private FragmentFriendsBinding binding;
    public Button homeBttn;
    public Button dashboardBttn;
    public Button notificationBttn;
    public Button btnFriends;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FriendsViewModel friendsViewModel =
                new ViewModelProvider(this).get(FriendsViewModel.class);

        binding = FragmentFriendsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final LinearLayout main = binding.main;
        createWraps();
        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle SavedInstance) {

        homeBttn = view.findViewById(R.id.button2);
        homeBttn.setVisibility(View.VISIBLE);

        dashboardBttn = view.findViewById(R.id.button3);
        dashboardBttn.setVisibility(View.VISIBLE);

        notificationBttn = view.findViewById(R.id.button4);
        notificationBttn.setVisibility(View.VISIBLE);

        (Content.getButton()).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FragmentFriends.this).navigate(R.id.action_navigation_friends_to_navigation_user_activity);;
            }
        });
        btnFriends = view.findViewById(R.id.button_friends); // Find the button by ID using the view passed to onViewCreated


        btnFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FragmentFriends.this).navigate(R.id.action_navigation_friends_to_navigation_friends_list);
            }
        });

        homeBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FragmentFriends.this).navigate(R.id.action_navigation_dashboard_to_navigation_home);
            }
        });

        notificationBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(FragmentFriends.this).navigate(R.id.action_navigation_dashboard_to_navigation_notifications);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void createWraps() {
        final LinearLayout main = binding.main;
        for (int i = 0; i < wrap_list.size(); i++) {
            Wraps wrap = wrap_list.get(i);
            if (wrap.getVisible() == YOU) {
                View view = LayoutInflater.from(main.getContext()).inflate(R.layout.wrap_widget, null, false);
                View details = ((ViewGroup) view).getChildAt(1);


                main.addView(view);
            } else {
                continue;
            }
        }



    }
}
