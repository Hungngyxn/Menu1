package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ActionMode mActionMode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.context);
        ImageButton mButton =  (ImageButton) findViewById(R.id.button_popup);
        textView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                if (mActionMode != null) {
                    return false;

                }
                mActionMode = MainActivity.this.startActionMode(mActionModeCallBack);
                view.setSelected(true);
                return true;
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, mButton);
                popup.getMenuInflater().inflate(
                        R.menu.menu_popup, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.reply_all:
                                showSetting();
                                Toast.makeText(MainActivity.this, "Text has been edited", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.forward:
                               showSetting();
                                Toast.makeText(MainActivity.this, "Text has been edited", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }

                    }
                    // implement click listener.
                });
                popup.show();

            }
            // define onClick
        });

    }

    public ActionMode.Callback mActionModeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            MenuInflater inflater = actionMode.getMenuInflater();
            inflater.inflate(R.menu.menu_context, menu);
            actionMode.setTitle("Choose your option");
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.context_share:
                    Toast.makeText(MainActivity.this, "Text has been shared", Toast.LENGTH_SHORT).show();
                    showFavorite();
                    actionMode.finish();
                    return true;
                case R.id.context_edit:
                    Toast.makeText(MainActivity.this, "Text has been edited", Toast.LENGTH_SHORT).show();
                    showSetting();
                    actionMode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            mActionMode = null;
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }
    public void showSetting(){
        Intent intent = new Intent(getApplicationContext(), content.class);
        startActivity(intent);
    }

    public void showFavorite(){
        Intent intent = new Intent(getApplicationContext(), content.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.option_settings:
                showSetting();
                Toast.makeText(MainActivity.this, "Setting successed", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option_favorites:
                showFavorite();
                Toast.makeText(MainActivity.this, "Add favorites successed", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}