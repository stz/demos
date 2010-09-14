function(doc) { 
   if (doc.type == "Order") {
	   emit(doc._id, doc);
   }
}