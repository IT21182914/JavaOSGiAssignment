package gymmanagementproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class Activator implements BundleActivator {

    private ServiceRegistration<GymService> gymServiceRegistration;

    @Override
    public void start(BundleContext context) throws Exception {
        GymService gymService = new GymServiceImpl();
        gymServiceRegistration = context.registerService(GymService.class, gymService, null);
        System.out.println("Gym Management Producer Bundle started. Gym service registered.");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        gymServiceRegistration.unregister();
        System.out.println("Gym Management Producer Bundle stopped. Gym service unregistered.");
    }
}
