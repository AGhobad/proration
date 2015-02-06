rem mvn clean compile  exec:java   -Dexec.args="$*"  -Dexec.mainClass=com.vonage.prorate.main.ProrationMain -Dexec.args="%1 %2 %3 %4 %5 %6"

mvn exec:java   -Dexec.args="$*"  -Dexec.mainClass=com.vonage.prorate.main.ProrationMain -Dexec.args="%1 %2 %3 %4 %5 %6"
