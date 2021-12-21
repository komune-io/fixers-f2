package f2.bdd.spring.autoconfigure.utils

interface ConsumerReceiver<T> {
	val items: MutableList<T>
}
