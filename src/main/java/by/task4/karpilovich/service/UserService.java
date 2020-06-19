package by.task4.karpilovich.service;

public interface UserService {
	
	public void deleteUser(Long[] id);
	
	public void blockUser(Long[] id);
	
	public void unblockUser(Long[] id);
}
