package com.example.a1039_1048_proyectoconjunto.aceptacion.R1;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.a1039_1048_proyectoconjunto.gestores.Gestor;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorServicios;
import com.example.a1039_1048_proyectoconjunto.gestores.GestorUbicaciones;
import com.example.a1039_1048_proyectoconjunto.servicios.ServicioOpenWeather;
import com.example.a1039_1048_proyectoconjunto.Ubicacion;

import org.junit.jupiter.api.Test;



import org.json.JSONException;
import org.json.JSONObject;

public class Historia5Test {

    @Test
    public void activarUbicacion_servicioDisponible_activar(){
        String json = "{   \"statename\" : {},   \"distance\" : \"0.015\",   \"elevation\" : \"120\",   \"osmtags\" : {      \"wikipedia\" : \"es:Cálig\",      \"source\" : \"BDLL25, EGRN, Instituto Geográfico Nacional\",      \"population\" : \"2217\",      \"idee_name\" : \"Càlig\",      \"ine_municipio\" : \"12034\",      \"is_in_region\" : \"Comunidad Valenciana\",      \"is_in_province\" : \"Castellón\",      \"boundary\" : \"administrative\",      \"wikidata\" : \"Q1635720\",      \"is_in\" : \"Castellón, Comunidad Valenciana, Spain\",      \"name\" : \"Càlig\",      \"name_ca\" : \"Càlig\",      \"is_in_country\" : \"Spain\",      \"admin_level\" : \"8\",      \"population_date\" : \"2009\",      \"type\" : \"boundary\"   },   \"state\" : \"Comunidad Valenciana\",   \"latt\" : \"40.46208\",   \"city\" : \"Calig\",   \"prov\" : \"ES\",   \"intersection\" : {      \"distance\" : \"0.061\",      \"xlat\" : \"40.46156\",      \"xlon\" : \"0.35439\",      \"street2\" : \"Diputacion\",      \"street1\" : \"CL FACUNDO COMES Y MAYOR\"   },   \"geocode\" : \"BENICARLO-UYFAV\",   \"geonumber\" : \"3162047685129\",   \"country\" : \"Spain\",   \"stnumber\" : \"3\",   \"staddress\" : \"CL SAN VICENTE\",   \"inlatt\" : \"40.46197\",   \"alt\" : {      \"loc\" : [         {            \"staddress\" : \"CL SAN VICENTE\",            \"stnumber\" : \"3\",            \"postal\" : \"12589\",            \"latt\" : \"40.46208\",            \"city\" : \"Calig\",            \"prov\" : \"Comunidad Valenciana\",            \"longt\" : \"0.35497\",            \"class\" : {}         },         {            \"staddress\" : \"San Vicente\",            \"stnumber\" : \"3\",            \"postal\" : {},            \"latt\" : \"40.46208\",            \"city\" : \"Calig\",            \"prov\" : \"Comunidad Valenciana\",            \"longt\" : \"0.35497\",            \"class\" : {}         },         {            \"staddress\" : \"San Vicente\",            \"stnumber\" : \"3\",            \"postal\" : {},            \"dist\" : \"0.015\",            \"latt\" : \"40.46208\",            \"city\" : \"Calig\",            \"prov\" : \"Comunidad Valenciana\",            \"longt\" : \"0.35497\",            \"class\" : {}         },         {            \"staddress\" : \"CL SAN VICENTE\",            \"stnumber\" : \"1\",            \"postal\" : \"12589\",            \"dist\" : \"0.020\",            \"latt\" : \"40.46208\",            \"city\" : \"Calig\",            \"prov\" : \"Comunidad Valenciana\",            \"longt\" : \"0.35506\",            \"class\" : {}         },         {            \"staddress\" : \"CL SAN LORENZO\",            \"stnumber\" : \"21\",            \"postal\" : \"12589\",            \"dist\" : \"0.025\",            \"latt\" : \"40.46207\",            \"city\" : \"Calig\",            \"prov\" : \"Comunidad Valenciana\",            \"longt\" : \"0.35514\",            \"class\" : {}         },         {            \"staddress\" : \"CL SAN LORENZO\",            \"stnumber\" : \"19\",            \"postal\" : \"12589\",            \"dist\" : \"0.017\",            \"latt\" : \"40.46202\",            \"city\" : \"Calig\",            \"prov\" : \"Comunidad Valenciana\",            \"longt\" : \"0.35506\",            \"class\" : {}         },         {            \"staddress\" : \"CL SAN ROQUE\",            \"stnumber\" : \"3\",            \"postal\" : \"12589\",            \"dist\" : \"0.037\",            \"latt\" : \"40.46229\",            \"city\" : \"Calig\",            \"prov\" : \"Comunidad Valenciana\",            \"longt\" : \"0.35499\",            \"class\" : {}         },         {            \"staddress\" : \"CL SAN VICENTE\",            \"stnumber\" : \"2\",            \"postal\" : \"12589\",            \"dist\" : \"0.029\",            \"latt\" : \"40.46222\",            \"city\" : \"Calig\",            \"prov\" : \"Comunidad Valenciana\",            \"longt\" : \"0.35497\",            \"class\" : {}         },         {            \"staddress\" : \"CL SAN ROQUE\",            \"stnumber\" : \"1\",            \"postal\" : \"12589\",            \"dist\" : \"0.032\",            \"latt\" : \"40.46225\",            \"city\" : \"Calig\",            \"prov\" : \"Comunidad Valenciana\",            \"longt\" : \"0.35497\",            \"class\" : {}         }      ]   },   \"timezone\" : \"Europe/Madrid\",   \"region\" : \"Calig, Comunidad Valenciana\",   \"postal\" : \"12589\",   \"poi\" : {      \"poilat\" : \"40.46195\",      \"name\" : \"Pub la Lluna\",      \"name_es\" : \"Pub La Lluna\",      \"name_ca\" : \"Pub la Lluna\",      \"addr_street\" : \"Calle San Tarsicio\",      \"amenity\" : \"pub\",      \"poilon\" : \"0.35477\",      \"internet_access\" : \"wlan\",      \"id\" : \"4827498823\",      \"poidist\" : \"0.009\"   },   \"longt\" : \"0.35497\",   \"remaining_credits\" : \"-177\",   \"confidence\" : \"0.9\",   \"inlongt\" : \"0.35487\",   \"class\" : {},   \"adminareas\" : {      \"admin7\" : {         \"wikipedia\" : \"es:Bajo Maestrazgo\",         \"is_in_region\" : \"Comunitat Valenciana\",         \"place\" : \"region\",         \"boundary\" : \"administrative\",         \"is_in_continent\" : \"Europe\",         \"wikidata\" : \"Q804017\",         \"is_in\" : \"Comunitat Valenciana;Spain;Europe\",         \"name\" : \"el Baix Maestrat\",         \"name_ca\" : \"el Baix Maestrat\",         \"name_es\" : \"Bajo Maestrazgo\",         \"is_in_country\" : \"Spain\",         \"admin_level\" : \"7\",         \"level\" : \"7\",         \"alt_name\" : \"Baix Maestrat\",         \"type\" : \"boundary\",         \"rank\" : \"region\"      },      \"admin6\" : {         \"is_in_region\" : \"Comunitat Valenciana\",         \"addr_country\" : \"ES\",         \"official_name_es\" : \"Provincia de Castellón\",         \"boundary\" : \"administrative\",         \"is_in_continent\" : \"Europe\",         \"name\" : \"Castelló / Castellón\",         \"ISO3166_2\" : \"ES-CS\",         \"is_in_country\" : \"Spain\",         \"level\" : \"6\",         \"official_name_en\" : \"Province of Castellón\",         \"type\" : \"boundary\",         \"name_eu\" : \"Castellón\",         \"name_fr\" : \"Castellón\",         \"source\" : \"BDLL25, EGRN, Instituto Geográfico Nacional\",         \"wikipedia\" : \"ca:Província de Castelló\",         \"place\" : \"county\",         \"is_in_country_code\" : \"ES\",         \"name_be\" : \"Кастэльён\",         \"name_ru\" : \"Кастельон\",         \"official_name_ca\" : \"Província de Castelló\",         \"official_name_fr\" : \"Province de Castellón\",         \"wikidata\" : \"Q54942\",         \"is_in_region_code\" : \"10\",         \"is_in\" : \"Europe;Spain;Comunitat Valenciana\",         \"name_es\" : \"Castellón\",         \"name_ca\" : \"Castelló\",         \"official_name\" : \"Provincia de Castelló/Castellón\",         \"ine_provincia\" : \"12\",         \"admin_level\" : \"6\",         \"official_name_de\" : \"Provinz Castellón\",         \"official_name_be\" : \"Правінцыя Кастэльён\",         \"border_type\" : \"province\"      },      \"admin8\" : {         \"wikipedia\" : \"es:Cálig\",         \"source\" : \"BDLL25, EGRN, Instituto Geográfico Nacional\",         \"population\" : \"2217\",         \"idee_name\" : \"Càlig\",         \"ine_municipio\" : \"12034\",         \"is_in_region\" : \"Comunidad Valenciana\",         \"is_in_province\" : \"Castellón\",         \"boundary\" : \"administrative\",         \"wikidata\" : \"Q1635720\",         \"is_in\" : \"Castellón, Comunidad Valenciana, Spain\",         \"name\" : \"Càlig\",         \"name_ca\" : \"Càlig\",         \"is_in_country\" : \"Spain\",         \"admin_level\" : \"8\",         \"level\" : \"8\",         \"population_date\" : \"2009\",         \"type\" : \"boundary\"      }   },   \"altgeocode\" : \"CALIG-UYFAV\"}";
        try {
            JSONObject jsonResponse = new JSONObject(json);
            boolean x  = jsonResponse.isNull("standard");
            System.out.println(x);
            String city = jsonResponse.getString("city");
            System.out.println(city);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*//Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        gestorServicios.setServicioOpenWeather(new ServicioOpenWeather());

        GestorUbicaciones gestorUbicaciones = GestorUbicaciones.getInstance();

        Gestor gestor = new Gestor(gestorUbicaciones, gestorServicios);

        String toponimo = "Castello";

        boolean dadoAlta = gestor.darAltaToponimo(toponimo);


        //When
        Ubicacion castellon = gestor.getUbicacion(toponimo);
        castellon.activar();


        //Then
        assertTrue(castellon.isActivada());*/

    }

    /*@Test
    public void activarUbicacion_servicioNoDisponible_activar(){
        //Given
        GestorServicios gestorServicios = GestorServicios.getInstance();
        GestorUbicaciones gestorUbicaciones = GestorUbicaciones.getInstance();

        Gestor gestor = new Gestor(gestorUbicaciones, gestorServicios);

        String toponimo = "Castello";

        boolean dadoAlta = gestor.darAltaToponimo(toponimo);


        //When
        Ubicacion castellon = gestor.getUbicacion(toponimo);
        castellon.activar();


        //Then
        assertFalse(castellon.isActivada());

    }
*/
}
