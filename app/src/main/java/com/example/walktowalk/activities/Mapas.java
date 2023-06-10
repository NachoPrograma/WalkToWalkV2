package com.example.walktowalk.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;
import com.example.walktowalk.R;
import com.example.walktowalk.clases.Itinerario;
import com.example.walktowalk.clases.Mapa;
import com.example.walktowalk.recyclerview.ItinerarioViewHolder;
import com.example.walktowalk.utilidades.MapaUtils;
import com.example.walktowalk.utilidades.PermissionUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// ayuda desde la web
// https://github.com/googlemaps/android-samples/tree/0ec4153b3766bc2fcecb0e2b638971544325aa87/tutorials/java/CurrentPlaceDetailsOnMap
public class Mapas extends AppCompatActivity implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback,
        GoogleMap.OnMarkerClickListener{
    private static final String TAG = Mapas.class.getSimpleName();
    private CameraPosition cameraPosition;
    // The entry point to the Places API.
    private PlacesClient placesClient;
    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient fusedLocationProviderClient;
    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private LatLng defaultLocation = null;
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;
    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location lastKnownLocation;
    // Keys for storing activity state.
    // [START maps_current_place_state_keys]
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    // [END maps_current_place_state_keys]
    // Used for selecting the current place.
    private static final int M_MAX_ENTRIES = 5;
    private String[] likelyPlaceNames;
    private String[] likelyPlaceAddresses;
    private List[] likelyPlaceAttributions;
    private LatLng[] likelyPlaceLatLngs;
    private MediaPlayer mediaPlayer;
    private ImageButton playButton;
    private ImageButton botonDerecha;
    private ImageButton botonIzquierda;
    private boolean isPlaying = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean permissionDenied = false;
    private GoogleMap mMap;
    ArrayList<Mapa> sitiosMapa;
    Mapa localizaciondefecto;
    private int ubicacionActual = 0; // Ubicación actual seleccionada
    private String itinerario_elegido;
    //--------------------------------------------------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sitiosMapa = MapasDesdeCSV();
        Intent intent = getIntent();
        if(intent != null) {
            Itinerario itinerario = (Itinerario) intent.getSerializableExtra(ItinerarioViewHolder.EXTRA_OBJETO_ITINERARIO);
            itinerario_elegido = itinerario.getId();
            setContentView(R.layout.activity_mapas);
            //ArrayList<Mapa> sitiosMapa = CiudadController.obtenerMapaDeItinerario(itinerario_elegido);
            ArrayList<Mapa> sitiosMapa = ObtenerMapa(itinerario_elegido);
            if (savedInstanceState != null) {
                lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
                cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
            }

            playButton = findViewById(R.id.playButton);
            botonDerecha = findViewById(R.id.bt_izquierda);
            botonIzquierda = findViewById(R.id.bt_derecha);

            //habilito places para crear las rutas
            if (!Places.isInitialized()) {
                Places.initialize(getApplicationContext(), getString(R.string.my_app_key));
                placesClient = Places.createClient(this);
            }
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            // obtengo los sitio del mapa
            localizaciondefecto = sitiosMapa.get(0);
            defaultLocation = new LatLng(localizaciondefecto.getX(),localizaciondefecto.getY());


            botonDerecha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ubicacionActual++;
                    if (ubicacionActual >= sitiosMapa.size()) {
                        ubicacionActual = 0; // Vuelve a la primera ubicación si no hay más ubicaciones
                    }
                    // Actualizar la ubicación en tu mapa o realizar otras acciones necesarias
                    Mapa ubicacionSeleccionada = sitiosMapa.get(ubicacionActual);
                    // Realizar acciones con la ubicación seleccionada
                }
            });
            botonIzquierda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ubicacionActual--;
                    if (ubicacionActual < 0) {
                        ubicacionActual = sitiosMapa.size() - 1; // Vuelve a la última ubicación si no hay más ubicaciones en la dirección izquierda
                    }
                    // Actualizar la ubicación en tu mapa o realizar otras acciones necesarias
                    Mapa ubicacionSeleccionada = sitiosMapa.get(ubicacionActual);
                    // Realizar acciones con la ubicación seleccionada
                }
            });
            playButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isPlaying) {
                        pauseAudio();
                    } else {
                        playAudio();
                    }
                }
            });
        }
    }
    @Override
    public boolean onMarkerClick(Marker marker) {
        Mapa mapa = (Mapa) marker.getTag();
        if (mapa != null) {
            String audioFileName = mapa.getNombreAudio();
            if (audioFileName != null) {
                // Obtener el identificador del recurso correspondiente al nombre del archivo de audio
                int audioResourceId = getResources().getIdentifier(audioFileName, "raw", getPackageName());

                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + audioResourceId));
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            // Reproducir el archivo de audio cuando esté preparado
                            mp.start();
                        }
                    });
                    mediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                    // Maneja la excepción de IO si ocurre
                    // Muestra un mensaje de error o realiza acciones adicionales según sea necesario
                }
            }

        }
        return false;
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, lastKnownLocation);
        }
        super.onSaveInstanceState(outState);
    }
    //--------------------------------------------------------------------------------------------------------------------------------------
    // METODOS PARA LOS MAPAS
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        //pongo mi localizacion actual en el mapa (punto azul)
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        enableMyLocation();
        // añadimos los lugares al mapa (puntos rojos)
        MapaUtils.addMarkes(mMap,sitiosMapa);
        //zoom hasta la posicion destino
        LatLng lpordefecto = new LatLng(localizaciondefecto.getX(), localizaciondefecto.getY());
        // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom( lpordefecto, DEFAULT_ZOOM));
        //-----------------------------------------------------------------------------------------
        this.mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Nullable
            @Override
            public View getInfoContents(@NonNull Marker marker) {
                View infoWindow = getLayoutInflater().inflate(R.layout.custom_info_contents,
                        (FrameLayout) findViewById(R.id.map), false);

                TextView title = infoWindow.findViewById(R.id.title);
                title.setText(marker.getTitle());

                TextView snippet = infoWindow.findViewById(R.id.snippet);
                snippet.setText(marker.getSnippet());
                return infoWindow;
            }
            @Override
            // Return null here, so that getInfoContents() is called next.
            public View getInfoWindow(Marker arg0) {
                return null;
            }
        });
        // [END map_current_place_set_info_window_adapter]
        // Prompt the user for permission.
        getLocationPermission();
        // [END_EXCLUDE]
        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();
        // Get the current location of the device and set the position of the map.
        getDeviceLocation();
    }
    // METODOS PARA HABILITAR LA LOCALIZACION
    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            // Permission to access the location is missing. Show rationale and request permission
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        }
    }
    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "el botón de localización ha sido pulsado ", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }
    @Override
    public void onMyLocationClick(@NonNull Location location) {
        // al darle a mi ubicación actual (punto azul) se ejecuta este código
        // Toast.makeText(this, "Mi localización actual es:\n" + location, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }
        if (PermissionUtils.isPermissionGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Permission was denied. Display an error message
            // Display the missing permission error dialog when the fragments resume.
            permissionDenied = true;
        }
        updateLocationUI();
    }
    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (permissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            permissionDenied = false;
        }
    }
    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
    // METODOS DEL MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mapa1_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.ir_mapa1_atras:
                finish();
                return true;
            case R.id.ir_mapa1_home:
                Intent intent1 = new Intent(this, Login.class);
                startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
//--------------------------------------------------------------------------------------------------------
    // METODOS PARA REALIZAR LAS RUTAS
    /**
     * Gets the current location of the device, and positions the map's camera.
     */
    // [START maps_current_place_get_device_location]
    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (locationPermissionGranted) {
                Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            lastKnownLocation = task.getResult();
                            if (lastKnownLocation != null) {
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(lastKnownLocation.getLatitude(),
                                                lastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                            }
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage(), e);
        }
    }
    // [END maps_current_place_get_device_location]
    /**
     * Prompts the user for permission to use the device location.
     */
    // [START maps_current_place_location_permission]
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }
    // [END maps_current_place_location_permission]
    /**
     * Handles the result of the request for location permissions.
     */
    /**
     * Prompts the user to select the current place from a list of likely places, and shows the
     * current place on the map - provided the user has granted location permission.
     */
    // [START maps_current_place_show_current_place]
    private void showCurrentPlace() {
        if (mMap == null) {
            return;
        }
        if (locationPermissionGranted) {
            // Use fields to define the data types to return.
            List<Place.Field> placeFields = Arrays.asList(Place.Field.NAME, Place.Field.ADDRESS,
                    Place.Field.LAT_LNG);
            // Use the builder to create a FindCurrentPlaceRequest.
            FindCurrentPlaceRequest request =
                    FindCurrentPlaceRequest.newInstance(placeFields);
            // Get the likely places - that is, the businesses and other points of interest that
            // are the best match for the device's current location.
            @SuppressWarnings("MissingPermission") final
            Task<FindCurrentPlaceResponse> placeResult =
                    placesClient.findCurrentPlace(request);
            placeResult.addOnCompleteListener (new OnCompleteListener<FindCurrentPlaceResponse>() {
                @Override
                public void onComplete(@NonNull Task<FindCurrentPlaceResponse> task) {
                    if (task.isSuccessful() && task.getResult() != null) {
                        FindCurrentPlaceResponse likelyPlaces = task.getResult();
                        // Set the count, handling cases where less than 5 entries are returned.
                        int count;
                        if (likelyPlaces.getPlaceLikelihoods().size() < M_MAX_ENTRIES) {
                            count = likelyPlaces.getPlaceLikelihoods().size();
                        } else {
                            count = M_MAX_ENTRIES;
                        }
                        int i = 0;
                        likelyPlaceNames = new String[count];
                        likelyPlaceAddresses = new String[count];
                        likelyPlaceAttributions = new List[count];
                        likelyPlaceLatLngs = new LatLng[count];
                        for (PlaceLikelihood placeLikelihood : likelyPlaces.getPlaceLikelihoods()) {
                            // Build a list of likely places to show the user.
                            likelyPlaceNames[i] = placeLikelihood.getPlace().getName();
                            likelyPlaceAddresses[i] = placeLikelihood.getPlace().getAddress();
                            likelyPlaceAttributions[i] = placeLikelihood.getPlace()
                                    .getAttributions();
                            likelyPlaceLatLngs[i] = placeLikelihood.getPlace().getLatLng();
                            i++;
                            if (i > (count - 1)) {
                                break;
                            }
                        }
                        // Show a dialog offering the user the list of likely places, and add a
                        // marker at the selected place.
                        Mapas.this.openPlacesDialog();
                    }
                    else {
                        Log.e(TAG, "Exception: %s", task.getException());
                    }
                }
            });
        } else {
            // The user has not granted permission.
            Log.i(TAG, "The user did not grant location permission.");
            // Add a default marker, because the user hasn't selected a place.
            mMap.addMarker(new MarkerOptions()
                    .title(getString(R.string.default_info_title))
                    .position(defaultLocation)
                    .snippet(getString(R.string.default_info_snippet)));
            // Prompt the user for permission.
            getLocationPermission();
        }
    }
    // [END maps_current_place_show_current_place]
    /**
     * Displays a form allowing the user to select a place from a list of likely places.
     */
    // [START maps_current_place_open_places_dialog]
    private void openPlacesDialog() {
        // Ask the user to choose the place where they are now.
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // The "which" argument contains the position of the selected item.
                LatLng markerLatLng = likelyPlaceLatLngs[which];
                String markerSnippet = likelyPlaceAddresses[which];
                if (likelyPlaceAttributions[which] != null) {
                    markerSnippet = markerSnippet + "\n" + likelyPlaceAttributions[which];
                }
                // Add a marker for the selected place, with an info window
                // showing information about that place.
                mMap.addMarker(new MarkerOptions()
                        .title(likelyPlaceNames[which])
                        .position(markerLatLng)
                        .snippet(markerSnippet));
                // Position the map's camera at the location of the marker.
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerLatLng,
                        DEFAULT_ZOOM));
            }
        };
        // Display the dialog.
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.pick_place)
                .setItems(likelyPlaceNames, listener)
                .show();
    }
    // [END maps_current_place_open_places_dialog]
    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */
    // [START maps_current_place_update_location_ui]
    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                lastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }
    private void playAudio() {
        if (ubicacionActual >= 0 && ubicacionActual < sitiosMapa.size()) {
            Mapa ubicacionSeleccionada = sitiosMapa.get(ubicacionActual);
            String audioFileName = ubicacionSeleccionada.getNombreAudio();

            if (audioFileName != null) {
                int audioResourceId = getResources().getIdentifier(audioFileName, "raw", getPackageName());

                if (audioResourceId != 0) {
                    mediaPlayer = MediaPlayer.create(this, audioResourceId);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            // Cuando el audio termina de reproducirse
                            isPlaying = false;
                            playButton.setImageResource(R.drawable.boton_play);
                        }
                    });
                    mediaPlayer.start();
                    isPlaying = true;
                    playButton.setImageResource(R.drawable.boton_pause);
                }
            }
        }
    }
    private void pauseAudio() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPlaying = false;
            playButton.setImageResource(R.drawable.boton_play);
        }
    }
    /*private byte[] obtenerAudioDesdeMySQL() {
        // Aquí deberás implementar la lógica para obtener los datos del archivo de audio desde MySQL
        // Esto implica establecer una conexión con la base de datos, ejecutar una consulta y recuperar el BLOB del archivo de audio
        // Devuelve los datos del archivo de audio en forma de byte[]
        return new byte[0];
    }*/


        // [END maps_current_place_update_location_ui]
//--------------------------------------------------------------------------------------------------------


    private ArrayList<Mapa> MapasDesdeCSV() {
        InputStream is = getResources().openRawResource(R.raw.mapas);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";

        ArrayList<Mapa> losMapas = new ArrayList<Mapa>();
        try {
            while ((line = reader.readLine()) != null) {
                String[] datos = line.split(",");
                String id = datos[0];
                String nombre = datos[1];
                double   x = Double.valueOf(datos[2]);
                double y = Double.valueOf(datos[3]);
                String id_ciudad = datos[4];
                String nombreAudio = datos[5]; // Agregar el nombre del archivo de audio desde la columna correspondiente en el CSV
                Mapa p = new Mapa(id, nombre, x, y, id_ciudad, nombreAudio);
                losMapas.add(p);
            }
        } catch (IOException e1) {
            System.out.println("no pude abrir el fichero de provincias");
            Log.i("csv", "no pude abrir el fichero de provincias");
        }
        return losMapas;
    }

    private ArrayList<Mapa> ObtenerMapa(String itinerarioSeleccionada) {
        ArrayList<Mapa> mapas = new ArrayList<Mapa>();

        for (Mapa p: sitiosMapa )
        {
            String nombre= p.getLocalizacion();
            String codigoComunidad = p.getIdItinerario();
            if(itinerarioSeleccionada.contains(codigoComunidad))
            {
                mapas.add(p);
            }
        }
        return mapas;
    }


}