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
        String json = "{\n" +
                "   \"statename\":{\n" +
                "      \n" +
                "   },\n" +
                "   \"distance\":\"0.188\",\n" +
                "   \"elevation\":\"62\",\n" +
                "   \"osmtags\":{\n" +
                "      \"wikipedia\":\"es:Sagunto (Valencia)\",\n" +
                "      \"source\":\"BDLL25, EGRN, Instituto Geográfico Nacional\",\n" +
                "      \"population\":\"66259\",\n" +
                "      \"idee_name\":\"Sagunto/Sagunt\",\n" +
                "      \"ine_municipio\":\"46220\",\n" +
                "      \"is_in_region\":\"Comunidad Valenciana\",\n" +
                "      \"is_in_province\":\"Valencia\",\n" +
                "      \"boundary\":\"administrative\",\n" +
                "      \"wikidata\":\"Q47483\",\n" +
                "      \"is_in\":\"Valencia, Comunidad Valenciana, Spain\",\n" +
                "      \"name\":\"Sagunt / Sagunto\",\n" +
                "      \"name_ca\":\"Sagunt\",\n" +
                "      \"name_es\":\"Sagunto\",\n" +
                "      \"is_in_country\":\"Spain\",\n" +
                "      \"admin_level\":\"8\",\n" +
                "      \"population_date\":\"2009\",\n" +
                "      \"type\":\"boundary\"\n" +
                "   },\n" +
                "   \"state\":\"Comunidad Valenciana\",\n" +
                "   \"latt\":\"39.69342\",\n" +
                "   \"city\":\"Sagunto\",\n" +
                "   \"prov\":\"ES\",\n" +
                "   \"geocode\":\"SAGUNTO-GPLAU\",\n" +
                "   \"geonumber\":\"3161957963704\",\n" +
                "   \"country\":\"Spain\",\n" +
                "   \"stnumber\":\"5\",\n" +
                "   \"staddress\":\"PL NUMERO 50\",\n" +
                "   \"inlatt\":\"39.69250\",\n" +
                "   \"alt\":{\n" +
                "      \"loc\":[\n" +
                "         {\n" +
                "            \"staddress\":\"PL NUMERO 50\",\n" +
                "            \"stnumber\":\"5\",\n" +
                "            \"postal\":\"46500\",\n" +
                "            \"latt\":\"39.69342\",\n" +
                "            \"city\":\"Sagunt/Sagunto\",\n" +
                "            \"prov\":\"Comunidad Valenciana\",\n" +
                "            \"longt\":\"-0.28092\",\n" +
                "            \"class\":{\n" +
                "               \n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"staddress\":\"PL NUMERO 50\",\n" +
                "            \"stnumber\":\"5\",\n" +
                "            \"postal\":\"46500\",\n" +
                "            \"latt\":\"39.69342\",\n" +
                "            \"city\":\"Sagunto\",\n" +
                "            \"prov\":\"Comunidad Valenciana\",\n" +
                "            \"longt\":\"-0.28092\",\n" +
                "            \"class\":{\n" +
                "               \n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"staddress\":\"PL NUMERO   50\",\n" +
                "            \"stnumber\":\"5\",\n" +
                "            \"postal\":{\n" +
                "               \n" +
                "            },\n" +
                "            \"latt\":\"39.69342\",\n" +
                "            \"city\":\"Sagunto\",\n" +
                "            \"prov\":\"Comunidad Valenciana\",\n" +
                "            \"longt\":\"-0.28092\",\n" +
                "            \"class\":{\n" +
                "               \n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"staddress\":\"Place Numero   50\",\n" +
                "            \"stnumber\":\"5\",\n" +
                "            \"postal\":{\n" +
                "               \n" +
                "            },\n" +
                "            \"latt\":\"39.69342\",\n" +
                "            \"city\":\"Sweet Hotel Els Arenals\",\n" +
                "            \"prov\":\"Comunidad Valenciana\",\n" +
                "            \"longt\":\"-0.28092\",\n" +
                "            \"class\":{\n" +
                "               \n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"staddress\":\"Autovía del Mediterráneo\",\n" +
                "            \"stnumber\":\"1\",\n" +
                "            \"postal\":{\n" +
                "               \n" +
                "            },\n" +
                "            \"dist\":\"0.271\",\n" +
                "            \"latt\":\"39.69388\",\n" +
                "            \"city\":\"Sagunto\",\n" +
                "            \"prov\":\"Comunidad Valenciana\",\n" +
                "            \"longt\":\"-0.28168\",\n" +
                "            \"class\":{\n" +
                "               \n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"staddress\":\"NUMERO 50 PL\",\n" +
                "            \"stnumber\":\"5\",\n" +
                "            \"postal\":{\n" +
                "               \n" +
                "            },\n" +
                "            \"dist\":\"0.189\",\n" +
                "            \"latt\":\"39.693418\",\n" +
                "            \"city\":\"Sagunto\",\n" +
                "            \"prov\":\"Comunidad Valenciana\",\n" +
                "            \"longt\":\"-0.2809247\",\n" +
                "            \"class\":{\n" +
                "               \n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"staddress\":\"Autovía del Mediterráneo\",\n" +
                "            \"stnumber\":\"1\",\n" +
                "            \"postal\":{\n" +
                "               \n" +
                "            },\n" +
                "            \"dist\":\"0.262\",\n" +
                "            \"latt\":\"39.69433\",\n" +
                "            \"city\":\"Sagunto\",\n" +
                "            \"prov\":\"Comunidad Valenciana\",\n" +
                "            \"longt\":\"-0.281\",\n" +
                "            \"class\":{\n" +
                "               \n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"staddress\":\"PL NUMERO 50\",\n" +
                "            \"stnumber\":\"5\",\n" +
                "            \"postal\":\"46500\",\n" +
                "            \"dist\":\"0.188\",\n" +
                "            \"latt\":\"39.69342\",\n" +
                "            \"city\":\"Sagunt/Sagunto\",\n" +
                "            \"prov\":\"Comunidad Valenciana\",\n" +
                "            \"longt\":\"-0.28092\",\n" +
                "            \"class\":{\n" +
                "               \n" +
                "            }\n" +
                "         },\n" +
                "         {\n" +
                "            \"staddress\":\"PL NUMERO 50\",\n" +
                "            \"stnumber\":\"4\",\n" +
                "            \"postal\":\"46500\",\n" +
                "            \"dist\":\"0.218\",\n" +
                "            \"latt\":\"39.69341\",\n" +
                "            \"city\":\"Sagunt/Sagunto\",\n" +
                "            \"prov\":\"Comunidad Valenciana\",\n" +
                "            \"longt\":\"-0.28133\",\n" +
                "            \"class\":{\n" +
                "               \n" +
                "            }\n" +
                "         }\n" +
                "      ]\n" +
                "   },\n" +
                "   \"timezone\":\"Europe/Madrid\",\n" +
                "   \"region\":\"Sagunto, Comunidad Valenciana\",\n" +
                "   \"postal\":\"46500\",\n" +
                "   \"longt\":\"-0.28092\",\n" +
                "   \"remaining_credits\":\"-176\",\n" +
                "   \"confidence\":\"0.9\",\n" +
                "   \"inlongt\":\"-0.27907\",\n" +
                "   \"class\":{\n" +
                "      \n" +
                "   },\n" +
                "   \"adminareas\":{\n" +
                "      \"admin7\":{\n" +
                "         \"wikipedia\":\"es:Campo de Murviedro\",\n" +
                "         \"is_in_region\":\"Comunitat Valenciana\",\n" +
                "         \"place\":\"region\",\n" +
                "         \"boundary\":\"administrative\",\n" +
                "         \"is_in_continent\":\"Europe\",\n" +
                "         \"wikidata\":\"Q925790\",\n" +
                "         \"is_in\":\"Comunitat Valenciana;Spain;Europe\",\n" +
                "         \"name\":\"el Camp de Morvedre\",\n" +
                "         \"name_ca\":\"el Camp de Morvedre\",\n" +
                "         \"name_es\":\"Campo de Murviedro\",\n" +
                "         \"is_in_country\":\"Spain\",\n" +
                "         \"admin_level\":\"7\",\n" +
                "         \"level\":\"7\",\n" +
                "         \"alt_name\":\"Camp de Morvedre\",\n" +
                "         \"type\":\"boundary\",\n" +
                "         \"rank\":\"region\"\n" +
                "      },\n" +
                "      \"admin6\":{\n" +
                "         \"name_de\":\"Valencia\",\n" +
                "         \"name_sr\":\"Валенсија\",\n" +
                "         \"is_in_region\":\"Comunidad Valenciana\",\n" +
                "         \"name_lt\":\"Valensija\",\n" +
                "         \"old_name_ca_valencia\":\"València\",\n" +
                "         \"official_name_es\":\"Provincia de Valencia\",\n" +
                "         \"name_uk\":\"Валенсія\",\n" +
                "         \"boundary\":\"administrative\",\n" +
                "         \"name_en\":\"Valencia\",\n" +
                "         \"name_el\":\"Βαλένθια\",\n" +
                "         \"is_in_continent\":\"Europe\",\n" +
                "         \"name_ca_valencia\":\"València\",\n" +
                "         \"name_lv\":\"Valensija\",\n" +
                "         \"name\":\"València / Valencia\",\n" +
                "         \"ISO3166_2\":\"ES-V\",\n" +
                "         \"is_in_country\":\"Spain\",\n" +
                "         \"alt_name_el\":\"Βαλένσια\",\n" +
                "         \"level\":\"6\",\n" +
                "         \"name_ar\":\"فالنسيا\",\n" +
                "         \"type\":\"boundary\",\n" +
                "         \"name_nl\":\"Valencia\",\n" +
                "         \"name_eu\":\"Valentzia\",\n" +
                "         \"name_fr\":\"Valence\",\n" +
                "         \"wikipedia\":\"es:Provincia de Valencia\",\n" +
                "         \"source\":\"BDLL25, EGRN, Instituto Geográfico Nacional\",\n" +
                "         \"is_in_country_code\":\"ES\",\n" +
                "         \"name_be\":\"Валенсія\",\n" +
                "         \"name_ru\":\"Валенсия\",\n" +
                "         \"official_name_ca\":\"Província de València\",\n" +
                "         \"name_la\":\"Valentia\",\n" +
                "         \"official_name_fr\":\"Province de Valence\",\n" +
                "         \"wikidata\":\"Q54939\",\n" +
                "         \"is_in_region_code\":\"10\",\n" +
                "         \"is_in\":\"Europe;Spain;Comunidad Valenciana\",\n" +
                "         \"official_name\":\"Provincia de València/Valencia\",\n" +
                "         \"name_ca\":\"València\",\n" +
                "         \"name_es\":\"Valencia\",\n" +
                "         \"ine_provincia\":\"46\",\n" +
                "         \"admin_level\":\"6\",\n" +
                "         \"name_pl\":\"Walencja\",\n" +
                "         \"official_name_de\":\"Provinz Valencia\",\n" +
                "         \"name_it\":\"Valencia\",\n" +
                "         \"official_name_be\":\"Правінцыя Валенсія\",\n" +
                "         \"border_type\":\"province\"\n" +
                "      },\n" +
                "      \"admin8\":{\n" +
                "         \"wikipedia\":\"es:Sagunto (Valencia)\",\n" +
                "         \"source\":\"BDLL25, EGRN, Instituto Geográfico Nacional\",\n" +
                "         \"population\":\"66259\",\n" +
                "         \"idee_name\":\"Sagunto/Sagunt\",\n" +
                "         \"ine_municipio\":\"46220\",\n" +
                "         \"is_in_region\":\"Comunidad Valenciana\",\n" +
                "         \"is_in_province\":\"Valencia\",\n" +
                "         \"boundary\":\"administrative\",\n" +
                "         \"wikidata\":\"Q47483\",\n" +
                "         \"is_in\":\"Valencia, Comunidad Valenciana, Spain\",\n" +
                "         \"name\":\"Sagunt / Sagunto\",\n" +
                "         \"name_ca\":\"Sagunt\",\n" +
                "         \"name_es\":\"Sagunto\",\n" +
                "         \"is_in_country\":\"Spain\",\n" +
                "         \"admin_level\":\"8\",\n" +
                "         \"level\":\"8\",\n" +
                "         \"population_date\":\"2009\",\n" +
                "         \"type\":\"boundary\"\n" +
                "      }\n" +
                "   },\n" +
                "   \"altgeocode\":\"SWEETELS-GPLAU\"\n" +
                "}";
        try {
            JSONObject jsonResponse = new JSONObject(json);
            boolean x  = jsonResponse.isNull("standard");
            System.out.println(x);
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
