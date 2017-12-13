Primeiro criei uma entity chamada Pessoa, com informações básicas para serem armazenadas no banco de dados. Além disso, declarei a chave primária como sendo o id da pessoa. Com isso o Room vai criar essa tabela com os atributos apresentados.  

@Entity(tableName = "Student_table")
public class Pessoa {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "surname")
    private String surname;

    @ColumnInfo(name = "marks")
    private String marks;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }
}

Após isso criei um objeto para acessar os dados, que possui manipulações do banco de dados. Room irá gerar uma implementação dos métodos declarados abaixo. Aqui implementei apenas as funcionalidades de inserir e deletar informações do bando de dados, além de consultas para saber quantos elementos temos e capturar todos eles.

@Dao
public interface PessoaDao {

    @Query("SELECT * FROM Student_table")
    List<Pessoa> getAll();

    @Query("SELECT COUNT(*) FROM Student_table")
    int countUsers();

    @Insert
    void insertAll(Pessoa... pessoas);

    @Delete
    void delete(Pessoa pessoa);
}

	Por último criei um classe que irá definir uma lista de entidades e versões do banco de dados.

@Database(entities = {Pessoa.class}, version = 1)  
public abstract class RNDatabase extends RoomDatabase {

    private static RNDatabase INSTANCE;
    public abstract PessoaDao pessoaDao();

    public static RNDatabase getRNDatabase(Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RNDatabase.class, "pessoa-database").allowMainThreadQueries().build();
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}

Agora posso realizar a operação de inserção desta maneira:  

RNDatabase db;  
Pessoa pessoa = new Pessoa();  
pessoa.setName(name);  
pessoa.setSurname(surname);  
pessoa.setMarks(marks);  

if(command.equals("insert")){


    db.pessoaDao().insertAll(pessoa);
}
