package com.jorge.app.ccm.ui.session;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jorge.app.ccm.R;
import com.jorge.app.ccm.controllers.ControllerVehicle;
import com.jorge.app.ccm.ui.user.User;
import com.jorge.app.ccm.ui.vehicles.Vehicle;
import com.jorge.app.ccm.utils.BrandsUtil;

import static com.jorge.app.ccm.ui.vehicles.VehiclesListActivity.VEHICLE_SELECT_FOR_SESION;

public class SesionDrivingActivity extends AppCompatActivity{

    private ControllerVehicle controllerVS;
    private ImageView imageViewLogoUser;
    private ImageView imageViewLogoBrand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sesion_driving );
        this.imageViewLogoUser = findViewById( R.id.imageView_logo_user_sesion_driving );
        this.imageViewLogoBrand = findViewById( R.id.imageView_logo_brand_sesion_driving );
        Resources resource = getResources();
        BrandsUtil brandsUtil = new BrandsUtil( resource );

       // controllerVehicle = new ControllerVehicle();

        Vehicle vehicleSelectForSesion = (Vehicle) getIntent().getExtras().getSerializable( VEHICLE_SELECT_FOR_SESION );//<- El Inten
        SesionDriving userSesionVehicle = new SesionDriving( true , vehicleSelectForSesion);

        User user = new User();

        //controllerVehicle.getControllerVehiclesSesion().newSesionsVehicleResgistry( userSesionVehicle );
        Glide.with(this).load(user.photoUri()).into(imageViewLogoUser);



      //  controllerVehicle.getControllerVehicleStatus().setDriving( vehicleSelectForSesion.getRegistrationNumber(), 1 );
        imageViewLogoBrand.setImageResource( vehicleSelectForSesion.getLogoVehicle() );

        System.out.println( user.photoUri() );


    }
}
