interface Cabinet {
    // zwraca dowolny element o podanej nazwie
    Optional<Folder> findFolderByName(String name);
    
    // zwraca wszystkie foldery podanego rozmiaru SMALL/MEDIUM/LARGE
    List<Folder> findFoldersBySize(String size);
    
    //zwraca liczbę wszystkich obiektów tworzących strukturę
    int count();
    }
    
    public class FileCabinet implements Cabinet {
    private List<Folder> folders;
    }
    
    interface Folder {
    String getName();
    String getSize();
    }
    
    interface MultiFolder extends Folder {
    List<Folder> getFolders();
    }
/////////////////////////////////////////////////////////////////////////////

public class FolderCabinet implements Cabinet{
    private List<Folder> folders;
    public FolderCabinet(List<Folder> folders){
        this.folders=folders
    }
    @Override
    public Optional<Folder>findFolderByName(String name){
        Queue<Folder> queue = new LinkedList<>(folders);
        while(!queue.isEmpty()){
            Folder folder = queue.poll();
            if(folder.getName().equals(name)){
                return Optional.of(folder)
            }
            if(folder instanceof MultiFolder)
            {
                queue.addAll(((MultiFolder)folder).getFolders())
            }
        }
        return Optional.empty();

    }
    @Override
    public List<Folder> findFoldersBySize(String size){
        Queue<Folder> queue = new LinkedList<>(folders)
        List<Folder> result = new ArrayList<>();
        while(!queue.isEmpty()){
            Folder folder = queue.poll();
            if(folder.getSize().equals(size)){
                result.add(folder);
            }
            if(folder instanceof MultiFolder){
                queue.addAll(((MultiFolder)folder).getFolders());
            }
        }
        return result;
    }
    @Override
    public int count(){
        Queue<Folder> queue = new LinkedList<>(folders);
        int count=0;
        while(!queue.isEmpty())
        {
            Folder folder=queue.poll();
            count++;
        
        if(folder instanceof MultiFolder){
            queue.addAll(((MultiFolder)folder).getFolders();
        }
    }
    return count;
    }
}
