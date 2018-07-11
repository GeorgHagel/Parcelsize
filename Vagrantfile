Vagrant.configure("2") do |config|
	
	config.vm.provider "virtualbox" do |v|
  		v.memory = 2048
  		v.cpus = 2
	end
	config.vm.box = "envimation/ubuntu-xenial-docker"
	config.vm.network "private_network", ip: "192.168.56.102"
	
end