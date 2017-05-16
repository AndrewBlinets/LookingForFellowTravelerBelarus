package com.andreiblinets.traveler.lookingforfellowtravelerbelarus.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.R;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.model.UserRegistration;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.network.TravelerAPI;
import com.andreiblinets.traveler.lookingforfellowtravelerbelarus.network.TravelerRestAdapter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class FragmentRegistration extends Fragment //implements View.OnClickListener
                                                 {

    private static int LAYOUT = R.layout.fragment_registration;
    private View view;
    private ImageButton imageButton;
    private Button regButton;
    private static final int REQUEST = 1;
    private String foto;
    private TravelerAPI api;
    private EditText editEmail;
                                                     private EditText editPhone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,container,false);
        api = TravelerRestAdapter.getApi();
        imageButton = (ImageButton) view.findViewById(R.id.avatarImage);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        regButton = (Button) view.findViewById(R.id.registrationButton);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registration();
                // checkingEmail();
            }
        });
        editEmail = (EditText) view.findViewById(R.id.emailEdit);
        editPhone = (EditText) view.findViewById(R.id.phoneEdit1);
        return view;
    }

     private void openGallery() {
         Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
         photoPickerIntent.setType("image/*");
         startActivityForResult(photoPickerIntent, REQUEST);
     }

     private void registration() {
         UserRegistration userRegistration = new UserRegistration();
         userRegistration.setFoto(foto);
         userRegistration.setAboutUser(((EditText)view.findViewById(R.id.aboutUserEdit)).getText().toString());
         userRegistration.setEmail(((EditText)view.findViewById(R.id.emailEdit)).getText().toString());
         userRegistration.setHashPassword(((EditText)view.findViewById(R.id.passwordEdit)).getText().toString());
         userRegistration.setName(((EditText)view.findViewById(R.id.nameEdit)).getText().toString());
         userRegistration.setSurName(((EditText)view.findViewById(R.id.surnameEdit)).getText().toString());
         userRegistration.setPhone(((EditText)view.findViewById(R.id.phoneEdit1)).getText().toString());
         Call<String> call = api.registration(userRegistration);
         call.enqueue(new Callback<String>() {
             @Override
             public void onResponse(Call<String> call, Response<String> response) {
                 String token = response.body();
             }

             @Override
             public void onFailure(Call<String> call, Throwable t) {
                 Toast.makeText(view.getContext(), "Нет доступак сети" +  t.getMessage(), Toast.LENGTH_SHORT).show();
             }
         });
     }

     private void checkingEmail()
     {
         Call<String> call = api.chekingEmail(editEmail.getText().toString());
         call.enqueue(new Callback<String>() {
             @Override
             public void onResponse(Call<String> call, Response<String> response) {
                 String token = response.body();
                 checkingPhone();
             }

             @Override
             public void onFailure(Call<String> call, Throwable t) {

             }
         });
     }

     private void checkingPhone() {
         Call<String> call = api.chekingPhone(editPhone.getText().toString());
         call.enqueue(new Callback<String>() {
             @Override
             public void onResponse(Call<String> call, Response<String> response) {
                 checkingPhone();
             }

             @Override
             public void onFailure(Call<String> call, Throwable t) {

             }
         });
     }

     @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap bitmap = null;
        //ImageView imageView = (ImageView) findViewById(R.id.imageView);

        switch(requestCode) {
            case REQUEST:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),selectedImage);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
                        foto = Base64.encodeToString(bos.toByteArray(), Base64.DEFAULT);//.toString();
                        // foto = Base64.encodeToString(bitmap.);//bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    byte[] rawBitmap = Base64.decode(foto.getBytes(), Base64.DEFAULT);
                    Bitmap bitmap1 = BitmapFactory.decodeByteArray(rawBitmap, 0, rawBitmap.length);
                    imageButton.setImageBitmap(bitmap1);
                }
        }
    }
}
