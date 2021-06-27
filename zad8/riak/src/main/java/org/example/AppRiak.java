package org.example;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.api.commands.kv.UpdateValue;
import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.RiakNode;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import org.example.model.Vehicle;

import java.net.UnknownHostException;

public class AppRiak {

    public static class VehicleUpdate extends UpdateValue.Update<Vehicle> {
        private final Vehicle update;

        public VehicleUpdate(Vehicle update) {
            this.update = update;
        }

        @Override
        public Vehicle apply(Vehicle t) {
            if (t == null) {
                t = new Vehicle();
            }

            t.setBrand(update.getBrand());
            t.setModel(update.getModel());
            t.setDateOfProduction(update.getDateOfProduction());
            t.setAvailable(update.getAvailable());

            return t;
        }
    }

    private static RiakCluster setUpCluster() throws UnknownHostException {
        RiakNode node = new RiakNode.Builder()
                .withRemoteAddress("127.0.0.1")
                .withRemotePort(8087)
                .build();

        RiakCluster cluster = new RiakCluster.Builder(node)
                .build();

        cluster.start();

        return cluster;
    }

    public static void main(String[] args) {
        try {
            RiakCluster cluster = setUpCluster();
            RiakClient client = new RiakClient(cluster);

            Vehicle vehicle = new Vehicle();
            vehicle.setBrand("Opel");
            vehicle.setModel("Corsa");
            vehicle.setAvailable(true);
            vehicle.setDateOfProduction(1990);

            Namespace myBucket = new Namespace("s22360");
            Location vehicleLocation = new Location(myBucket, "corsa");

            // Dodanie
            StoreValue storeVehicleOp = new StoreValue.Builder(vehicle)
                    .withLocation(vehicleLocation)
                    .build();
            client.execute(storeVehicleOp);
            System.out.println("Pojazd o kluczu 'corsa' dodany do kubełka s22360");


            // Pobranie
            FetchValue fetchOp = new FetchValue.Builder(vehicleLocation)
                    .build();
            Vehicle fetchedObject = client.execute(fetchOp).getValue(Vehicle.class);
            System.out.println("Pobranie pojazdu o kluczu 'corsa' z kubełka, rezultat: " + fetchedObject);


            // Modyfikacja
            System.out.println("Modyfikacja isAvailable na wartość 'false'");
            fetchedObject.setAvailable(false);
            VehicleUpdate updatedVehicle = new VehicleUpdate(fetchedObject);
            UpdateValue updateValue = new UpdateValue.Builder(vehicleLocation)
                    .withUpdate(updatedVehicle).build();
            UpdateValue.Response response = client.execute(updateValue);

            // Ponowne pobranie
            fetchedObject = client.execute(fetchOp).getValue(Vehicle.class);
            System.out.println("Pobranie pojazdu po modyfikacji o kluczu 'corsa' z kubełka, rezultat: " + fetchedObject);


            // Usunięcie
            DeleteValue deleteOp = new DeleteValue.Builder(vehicleLocation)
                    .build();
            client.execute(deleteOp);

            // Ponowne pobranie
            fetchedObject = client.execute(fetchOp).getValue(Vehicle.class);
            System.out.println("Pobranie pojazdu po usunięciu o kluczu 'corsa' z kubełka, rezultat: " + fetchedObject);

            cluster.shutdown();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
