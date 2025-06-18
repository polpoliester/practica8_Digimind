package vazquez.paul.mydigimind_vazquezpaul
import java.io.Serializable

data class Recordatorio(var dias: String,
                        var tiempo: String,
                        var nombre: String) : Serializable
