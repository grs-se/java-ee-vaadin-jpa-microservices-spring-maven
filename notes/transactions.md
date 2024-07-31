# Transactions

- transaction means a sequence of information exchange and related work that is **treated as a single unit of work**  
- **IT ENSURES DATABASE INTEGRITY!!!**  
- a transaction has to be **entirely complete** otherwise there will be a rollback.  

---
- We should include all of the SQL statements within a **transaction** 
- we want to make sure if one of the statements fail then we rollback and make no change at all
- **WE WANT TO AVOID INCONSISTENT STATES!!!**  
---

```java
@Transactional
public method saveStudent() {
	Person person = loadPerson(personId);
	Address address = loadAddress(person);
	
	updatePersonAddress(address);
	savePerson(person);
}

```
- every single operation within the given method will belong to the same transaction which means that either all of the operatiosn will be successful or none of the operations will be executed at all.
- @Transactional JPA annotation, but delegates all the operations to JDBC
- it will set connection.setAutoCommit(false) = not to commit every single query independently but to handle all the operations as a single unit of work.
- then after that we can confirm what operations we want to execute
- Spring handles the IoC container Spring will inject the dependency the UserService object with so called proxies - whenever we autowire, i.e inject the given object that is annotated with @Transactional then underthehood Spring is going to create a Proxy class that will be injected into the user controller, and this proxy will delegate all the operations to the user service, and it is going to handle the openTransaction operation and closeTransaction operation.
- if we understand that underthehood Spring is going to create a proxy class then we can understand several of the behaviours of the transactional annotation.

```java
@Service
@Transactional(readOnly = true) // all methods of this class will inherit transactional behaviour
// readonly = A boolean flag that can be set to true if the transaction iseffectively read-only, allowing for corresponding optimizations at runtime. 
public class StatusServiceImpl implements StatusService {
	
	@Autowired
	private StatusRepository statusRepository;

	@Override
	// default value is readOnly = false
	// save method is not readonly because it changes values in the db
	@Transactional
	public void save(Status status) {
		statusRepository.save(status);
	}

	@Override
	public List<Status> findAll() {
		return statusRepository.findAll();
	}

}

```