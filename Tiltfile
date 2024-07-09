# Build
custom_build(
   # Name of the container image
   ref = 'order-service',
   # Command to build the container image
   # On Windows, replace $EXPECTED_REF with %EXPECTED_REF%
   command = 'mvn spring-boot:build-image -Dspring-boot.build-image.imageName=$EXPECTED_REF',
   # Files to watch that trigger a new build
   deps = ['pom.xml', 'src']
)

# Deploy
k8s_yaml(['k8s/deployment.yml', 'k8s/service.yml'])

# Manage
k8s_resource('order-service', port_forwards=['9002'])