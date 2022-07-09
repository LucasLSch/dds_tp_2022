package domain.organization;

public interface Visitado {
	public default Double acceptVisitor(VisitorHc Avisitor){//?
		return 0.0; //??? necesito que los metodos devuelvan double pero aca como hago???
	}
}
