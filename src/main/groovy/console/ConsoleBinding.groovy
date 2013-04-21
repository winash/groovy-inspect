package console

import core.Inspector

/**
 * @author Winash
 */
class ConsoleBinding {


    def list(){
       return Inspector.store.keySet().collect {"${it}-(${Inspector.store.get(it).size()} instances)"}
    }


    def help(){
        return ["list -> Show all available classes","help - > Show this help!"]
    }

    def show(String name){
        if (!Inspector.store.containsKey(name))
            return "No such Class was found"
        return Inspector.store.get(name);
    }

    def load(String name,int idx){
        if (!Inspector.store.containsKey(name))
            return "No such Class was found"
        return Inspector.store.get(name).get(idx);
    }


}
