import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.Map;


class Monedas {
    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    public void setConversionRates(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }

    public Double getARS() {
        return conversionRates.get("ARS");
    }

    public Double getBRL() {
        return conversionRates.get("BRL");
    }

    public Double getCOP() {
        return conversionRates.get("COP");
    }


    @Override
    public String toString() {
        return "Monedas{" +
                "conversionRates=" + conversionRates +
                '}';
    }
}

public class Conversor {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner lectura = new Scanner(System.in);
        int x = 0;
        double saldo = 0;
        double monto = 0;


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/5644134ab47d17b44b9c51e4/latest/USD"))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        // System.out.println(json);
        Gson gson = new Gson();


        Monedas Cambio = gson.fromJson(json, Monedas.class);
        // System.out.println(Cambio.getARS());
        // System.out.println(Cambio.getCOP());
        // System.out.println(Cambio);

        System.out.println("************************************");
        System.out.println("Bienvenido al conversor de Monedas!");
        System.out.println("-Elija una opcion-");
        System.out.println("************************************");

        while (x != 7) {

            System.out.println("****************************");
            System.out.println("1. Dolar =>> Peso Argentino");
            System.out.println("2. Peso Argentino =>> Dolar");
            System.out.println("3. Dolar=>> Real Brasilenio");
            System.out.println("4. Real Brasilenio =>> Dolar");
            System.out.println("5. Dolar =>> Peso Colombiano");
            System.out.println("6. Peso Colombiano =>> Dolar");
            System.out.println("7. Salir");
            System.out.println("****************************");

            System.out.println("Ingrese una opcion:");
            x = lectura.nextInt();

            switch (x) {
                case 1:
                    System.out.println("Ingrese el monto en dolares a calcular:");
                    monto = lectura.nextDouble();
                    saldo = monto * Cambio.getARS();
                    System.out.println("El total en pesos es: " + saldo);
                    break;
                case 2:
                    monto =0;
                    System.out.println("Ingrese el monto en pesos a calcular:");
                    monto = lectura.nextDouble();
                    saldo = monto / Cambio.getARS();
                    System.out.println();
                    System.out.println("El total en dolares es: " + saldo );
                    System.out.println();
                    break;

                case 3:
                    monto =0;
                    System.out.println("Ingrese el monto en Dolares a convertir a reales:");
                    monto = lectura.nextDouble();
                    saldo = monto * Cambio.getBRL();
                    System.out.println();
                    System.out.println("El total en Reales es: " + saldo );
                    System.out.println();
                    break;

                case 4:
                    monto =0;
                    System.out.println("Ingrese el monto en Reales a convertir a Dolares:");
                    monto = lectura.nextDouble();
                    saldo = monto / Cambio.getBRL();
                    System.out.println();
                    System.out.println("El total en Dolares es: " + saldo );
                    System.out.println();
                    break;

                case 5:
                    monto =0;
                    System.out.println("Ingrese el monto en Pesos Colombianos a convertir a Dolares:");
                    monto = lectura.nextDouble();
                    saldo = monto / Cambio.getCOP();
                    System.out.println();
                    System.out.println("El total en Dolares es: " + saldo );
                    System.out.println();
                    break;

                case 6:
                    monto =0;
                    System.out.println("Ingrese el monto en Dolares a convertir a Pesos Colombianos:");
                    monto = lectura.nextDouble();
                    saldo = monto * Cambio.getCOP();
                    System.out.println();
                    System.out.println("El total en Pesos Colombianos es: " + saldo );
                    System.out.println();
                    break;

                case 7:
                    System.out.println("Gracias por usar nuestros servicios.");
                    break;

                default:
                    System.out.println("No es una Opcion Valida");
            }


        }
    }
}